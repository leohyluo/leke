package com.sugar.leke.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author luohongyun
 *
 */
public class BeanCopierUtil {

	public static void copy(Object srcObj, Object destObj, Converter converter) {
		BeanCopier copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), converter != null);
		copier.copy(srcObj, destObj, converter != null ? converter : null);
	}

	public static void copy(Object srcObj, Object destObj) {
		copy(srcObj, destObj, null);
	}

	public static <T, R> void copyPropertiesWithoutNull(T source, R target) {
		Field[] sourceFields = source.getClass().getDeclaredFields();
		Field[] targetFields = target.getClass().getDeclaredFields();
		Map<String, Field> sourceFieldMap = Stream.of(sourceFields).collect(Collectors.toMap(Field::getName, Function.identity()));
		Map<String, Field> targetFieldMap = Stream.of(targetFields).collect(Collectors.toMap(Field::getName, Function.identity()));
		sourceFieldMap.forEach((fieldName, field) -> {
			//字段名称一样才允许赋值
			if(targetFieldMap.containsKey(fieldName)) {
				String sourceFileType = field.getType().getName();
				String targetFileType = targetFieldMap.get(fieldName).getType().getName();
				//字段类型一致才能赋值
				if(sourceFileType.equals(targetFileType)) {
					Class<?> fieldType = field.getType();
					String upperFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					String getter = "get" + upperFieldName;
					String setter = "set" + upperFieldName;
					try {
						Method sourceGetter = source.getClass().getDeclaredMethod(getter);
						Object fieldValue = sourceGetter.invoke(source);
						//源字段的值不为空才赋给目标字段
						if(fieldValue != null && StringUtils.isNotEmpty(fieldValue.toString())) {
							Method targetSetter = target.getClass().getDeclaredMethod(setter, fieldType);
							targetSetter.invoke(target, fieldValue);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	public static <T, R> R uniformCopy(T source, Supplier<R> supplier) {
		R r = supplier.get();
		copy(source, r);
		return r;
	}

}
