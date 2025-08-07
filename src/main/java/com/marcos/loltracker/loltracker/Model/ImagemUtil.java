package com.marcos.loltracker.loltracker.Model;

import java.io.File;

public class ImagemUtil {

    public static String encontrarSplash(String nomeCampeao) {
        String[] extensoes = { ".jpg", ".png", ".webp" };
        String basePath = "src/main/resources/static/splashArt/Campeoes/" + nomeCampeao.toLowerCase() + "/";


        for (String ext : extensoes) {
            File arquivo = new File(basePath + "splashart" + ext);
            if (arquivo.exists()) {
                return "/splashArt/" + nomeCampeao.toLowerCase() + "/splashart" + ext;
            }
        }

        return "/Campeoes/interrogacao.png";
    }
}
