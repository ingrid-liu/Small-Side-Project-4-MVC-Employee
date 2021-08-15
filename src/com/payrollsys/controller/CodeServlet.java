package com.payrollsys.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * CodeServlet loads the VERIFICATION CODE and generates a dynamic verification img in login.jsp.
 * @author  'Ingrid' Xiaoying Liu & Ryan Mah
 * @version 1.0
 * @since   8/6/21
 */

@WebServlet("/codeServlet")
public class CodeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.service(req, resp);
        /*
        Generate random picture verification code
        1. draw numbers
        2. choose different color for the nums
        3. change font if needed
        Reference:https://developpaper.com/generate-random-picture-verification-code-in-java/
        Java provides a drawing tool: BufferedImage
         */

        //use built-in package image.BufferedImage to create verification img
        int width = 110, height = 30;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        //get the context of the img
        Graphics g = image.getGraphics();

        //set the background color (otherwise it is black)
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        //set font
        g.setFont(new Font("Arial", Font.PLAIN, 20));       //Font.PLAIN font style

        //randomly generate 200 interference lines to make the verification img hard to detect
        Random random = new Random();       // generate a random object
        g.setColor(getRandColor(160, 200));     // set interference lines' color
        for (int i = 0; i < 200; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);   // two coordinates to draw one line
        }

        // randomly generate 6-digit verification code
        String sRand = "";
        for (int i = 0; i < 6; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // show the code in the img
            g.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            // todo Question:
            // The colors from the calling function are the same, maybe because the seeds are too closed,
            // so they can only be generated directly
            g.drawString(rand, 13 * i + 6, 16);
        }


        //Graph object dispose (https://stackoverflow.com/questions/13911743/why-does-calling-dispose-on-graphics-object-cause-jpanel-to-not-render-any-com)
        g.dispose();
        try {
            ImageIO.write(image, "JPEG", resp.getOutputStream());
        } catch (Exception e) {
            System.out.println("An error occurred:" + e.toString());
        }
        //save the verification code to Session (submit with the ID/PSW and do the match work in next step)
        req.getSession().setAttribute("randStr", sRand);

    }



    /**
     * The getRandColor function randomly gets a color.
     * @param fc an integer to define the color space value
     * @param bc an integer to define the color space value
     * @return a Color object
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
