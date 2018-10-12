package com.sugar.leke.service;

import com.alibaba.fastjson.JSONObject;
import com.sugar.leke.util.HttpUtils;
import com.sugar.leke.wechar.WecharConstant;
import com.sugar.leke.wechar.WecharTemplate;
import com.sugar.leke.wechar.dto.AccessTokenDTO;
import com.sugar.leke.wechar.dto.AuthDTO;
import com.sugar.leke.wechar.dto.QRCodeDTO;
import com.sugar.leke.wechar.dto.TicketDTO;
import com.sugar.leke.wechar.util.WecharUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public interface OfficalAccountService {

	public static final Logger logger = LoggerFactory.getLogger(OfficalAccountService.class);

	/**
	 * 获取临时二维码
	 * @param
	 * @return
	 */
	QRCodeDTO getTempQRCode(String userId, Long diagnosisId);
	
	/**
	 * 推送排队信息
	 * @param
	 */
	void sendQueueInfo(String wecharId, WecharTemplate template, String data);
	
	/**
	 * 通过code换取网页授权access_token
	 * @param code 第一步获取的code参数
	 * @param 
	 * @return
	 */
	AuthDTO getAuthAccessToken(String code);
	
	/**
	 * 开发者模式，创建菜单
	 */
	void createMenu();
	
	/**
	 * 获取Token
	 * @return
	 * @throws
	 * @throws IOException
	 */
	default AccessTokenDTO getAccessToken(int flushFlag) throws  IOException{
		AccessTokenDTO tokenDto = WecharUtils.getAccessTokenDTO();
		if(tokenDto != null && !WecharUtils.AccessTokenExpired(tokenDto) && 1 != flushFlag) {
			return tokenDto;
		}
		logger.info("开始调用微信服务器获取AccessToken");
		String uri = WecharConstant.TOKEN_URL.concat("?grant_type=client_credential")
				.concat("&appid=").concat(WecharConstant.APPID).concat("&secret=")
				.concat(WecharConstant.SECRET);
		
		String result = HttpUtils.doGet(uri);
		logger.info("调用微信服务器获取AccessToken,微信返回:" + result);
        tokenDto = JSONObject.parseObject(result, AccessTokenDTO.class);
		WecharUtils.saveAccessTokenDTO(tokenDto);
		return tokenDto;
	}
	
	/**
	 * 创建二维码ticket
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	default TicketDTO getTicket(String userId) throws ClientProtocolException, IOException {
		AccessTokenDTO accessToken = getAccessToken(0);
		if(accessToken == null) {
			return null;
		}
		String token = accessToken.getAccess_token();
		String uri = WecharConstant.TICKET_URL.concat("?access_token=").concat(token);
		JSONObject json = new JSONObject();
		//该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒
		json.put("expire_seconds", 2592000);
		//二维码类型，QR_SCENE为临时的整型参数值，QR_STR_SCENE为临时的字符串参数值，QR_LIMIT_SCENE为永久的整型参数值，QR_LIMIT_STR_SCENE为永久的字符串参数值
		json.put("action_name", "QR_SCENE");
		//场景信息
		JSONObject actionInfo = new JSONObject();
		//二维码详细信息
		JSONObject sceneInfo = new JSONObject();
		sceneInfo.put("scene_id", userId);
		
		actionInfo.put("scene", sceneInfo);
		
		json.put("action_info", actionInfo);
		String params = json.toJSONString();
		String result = HttpUtils.doPost(uri, params);
		System.out.println("获取Ticket，微信返回"+result);
		TicketDTO ticketDto = JSONObject.parseObject(result, TicketDTO.class);
		return ticketDto;
	}
}
