package com.kgc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/18/ 23:15
 * @Description
 */
@Controller
public class CommonController {
    private Logger logger=Logger.getLogger(getClass());
    @RequestMapping("/doImg")
    public void doImg(String filePath,HttpServletResponse response) {
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
