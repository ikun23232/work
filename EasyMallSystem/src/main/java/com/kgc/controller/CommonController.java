package com.kgc.controller;

import com.kgc.utils.ReplayUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;

/**
 * @author: 欧洋宏
 * @create: 2024-03-19 10:33
 **/
@Controller
public class CommonController {
    private Logger logger=Logger.getLogger(getClass());

    @Autowired
    private ReplayUtil replayUtil;

    @RequestMapping("/getUUID")
    @ResponseBody
    public String getUUID(){
        replayUtil.checkRandom("");
        String random = replayUtil.getRandom();
        logger.info(random);
        return random;
    }
    @RequestMapping("/doImg")
    public void doImg(String filePath, HttpServletResponse response) {
        logger.info("UserController doImg is start...filePath" + filePath);
        ServletOutputStream os = null;
        try {
            if (filePath == null) {
                return;
            }
            filePath = URLDecoder.decode(filePath, "utf-8");


            InputStream is = null;

            is = new FileInputStream(filePath);

            os = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int length;
            while ((length = is.read(bytes)) != -1) {
                os.write(bytes, 0, length);
            }
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
        } catch (Exception e) {
            logger.debug("UserController doImg is start...filePath" + filePath);


        }
        logger.info("UserController doImg is end...filePath" + filePath);
    }

}
