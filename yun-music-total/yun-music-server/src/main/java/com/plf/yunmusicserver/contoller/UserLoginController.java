package com.plf.yunmusicserver.contoller;

import cn.hutool.http.GlobalHeaders;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicentity.dto.IdentifyCertificatesRequestDTO;
import com.plf.yunmusicentity.dto.IdentifyCertificatesResponseDTO;
import com.plf.yunmusicentity.dto.IdentifyCertificatesResponseVO;
import com.plf.yunmusicentity.dto.InnerOCRConstant;
import com.plf.yunmusicentity.dto.user.UserDTO;
import com.plf.yunmusicserver.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@RestController
@Slf4j
@RequestMapping(value = "api/userLogin")
@Api(value = "用户登录，注册控制器", tags = "用于用户登录，注册接口控制")
public class UserLoginController {

    public static final String ocrUrl = "https://demo.sahal.ai/api/v1/full-kyc-simplified/documents/download?reference=704240ee-dc95-4ec1-bcb3-ca94c1af5595";

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private RestTemplate restTemplate;


    @ApiOperation(value = "【登录】用户登录接口")
    @GetMapping(value = "loginIn")
    public ResponseResult<UserDTO> userLoginIn(@RequestParam(value = "recode") String recode,
                                               @RequestParam(value = "password") String password) {
        return userLoginService.loginIn(recode, password);
    }

    @PostMapping("/third/identify/certificates")
    public ResponseResult<IdentifyCertificatesResponseDTO> certificates(@RequestBody IdentifyCertificatesRequestDTO requestDTO) {
        log.info("inner ocr scan service requestParam:{}", requestDTO);
        IdentifyCertificatesResponseVO ocrResponseVO = httpRequest(requestDTO.getUserId(), requestDTO.getFile());
        if (Objects.isNull(ocrResponseVO)){
            log.error("ocr识别返回空userId={}",requestDTO.getUserId());
            return ResponseResult.error("001","fas");
        }
        IdentifyCertificatesResponseVO.Recognition recognition = ocrResponseVO.getRecognition();
        if (!"ok".equals(ocrResponseVO.getStatus()) || Objects.isNull(recognition)){
            return ResponseResult.error("001","fas");
        }
        //根据返回值设置VO对象数据
        IdentifyCertificatesResponseDTO innerOCRResponseVO = new IdentifyCertificatesResponseDTO();
        for (IdentifyCertificatesResponseVO.FieldData fieldData : recognition.getList()) {
            if (fieldData.getFieldName().equals(InnerOCRConstant.CNIC)) {
                innerOCRResponseVO.setIdCardNo(fieldData.getFieldDetail().getText());
            }
            if (fieldData.getFieldName().equals(InnerOCRConstant.FIRST_NAME)) {
                innerOCRResponseVO.setFirstName(fieldData.getFieldDetail().getText());
            }
            if (fieldData.getFieldName().equals(InnerOCRConstant.GENDER)) {
                //TODO 性别枚举值待确认
                //innerOCRResponseVO.setGender();
            }
            if (fieldData.getFieldName().equals(InnerOCRConstant.FATHER_NAME)) {
                innerOCRResponseVO.setLastName(fieldData.getFieldDetail().getText());
            }
        }
        log.info("inner ocr scan service responseParam:{}", innerOCRResponseVO);
        return ResponseResult.success(innerOCRResponseVO);
    }

    private IdentifyCertificatesResponseVO httpRequest(Long userId, String fileBase64) {
        log.info(" ocr httpRequest param userId:{}", userId);
        JSONObject map = new JSONObject();
//        map.put(InnerOCRConstant.IMAGE_BASE64, fileBase64);
//        map.put(InnerOCRConstant.USER_ID, String.valueOf(userId));
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth("live_YOekbS4MfU8QZspLun2SRpgewgrHXKKyGAa");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>("",headers);
        map.put("Authorization","Bearer live_YOekbS4MfU8QZspLun2SRpgewgrHXKKyGAa");

        ResponseEntity<String> forEntity = restTemplate.getForEntity(ocrUrl, String.class, map);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(ocrUrl, httpEntity, String.class);
        //ResponseEntity<IdentifyCertificatesResponseVO> stringResponseEntity = restTemplate.postForEntity(ocrUrl, map.toString(), IdentifyCertificatesResponseVO.class);

        // 请求头设置,APPLICATION_JSON_UTF8格式的数据
//        GlobalHeaders.INSTANCE.header("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//        GlobalHeaders.INSTANCE.header("Host","");
//        JSONObject map = new JSONObject();
//        map.put(InnerOCRConstant.IMAGE_BASE64, fileBase64);
//        map.put(InnerOCRConstant.USER_ID, String.valueOf(userId));
//        GlobalHeaders.INSTANCE.header("Content-Length",String.valueOf(map.toString().length()));
//        String result = HttpUtil.post(ocrUrl, map);
//        log.info("third party OCR response result:{}", result);
//        if (Strings.isNotBlank(result)){
//            return new IdentifyCertificatesResponseVO();
//        }
        return null;
    }
}
