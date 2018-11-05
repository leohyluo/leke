package com.sugar.leke.wechar;


import com.sugar.leke.wechar.dto.CallBackDTO;

public interface CallBackHandler<T extends CallBackDTO> {

	 String handle(T t);

}
