package com.Reference.teamproject1;

import java.util.Random;

public class HairStyles {
    private static final Random RANDOM = new Random();

    public static int getRandomHairStyleDrawable() {
        switch (RANDOM.nextInt(30)) {
            default:
            case 0:
                return R.drawable.asperm_kimubin;
            case 1:
                return R.drawable.babyperm_song;
            case 2:
                return R.drawable.dandy_hyunbin;
            case 3:
                return R.drawable.dandy_kimubin;
            case 4:
                return R.drawable.dandy_lee;
            case 5:
                return R.drawable.dandy_yonghwa;
            case 6:
                return R.drawable.moheekan_onebin;
            case 7:
                return R.drawable.naturalperm;
            case 8:
                return R.drawable.part_hyunbin;
            case 9:
                return R.drawable.partperm_gongu;
            case 10:
                return R.drawable.partperm_hyunhin;
            case 11:
                return R.drawable.partperm_lee;
            case 12:
                return R.drawable.partperm_lee2;
            case 13:
                return R.drawable.perm;
            case 14:
                return R.drawable.pomade_lee;
            case 15:
                return R.drawable.regent_hyunbin;
            case 16:
                return R.drawable.regent_kimubin;
            case 17:
                return R.drawable.regent_onebin;
            case 18:
                return R.drawable.regent_uain;
            case 19:
                return R.drawable.short_regent_song;
            case 20:
                return R.drawable.softdandyperm_kimubin;
            case 21:
                return R.drawable.softpartperm_kimubin_;
            case 22:
                return R.drawable.swat_hyunbin;
            case 23:
                return R.drawable.volumemagic_onebin;
            case 24:
                return R.drawable.volumeperm_kimubin;
            case 25:
                return R.drawable.volumeperm_song;
            case 26:
                return R.drawable.volumeperm_song2;
            case 27:
                return R.drawable.comma;
            case 28:
                return R.drawable.comma_park;
            case 29:
                return R.drawable.comma_u;
        }
    }

    public static final String[] sHairStyleStrings = {
            "댄디컷", "가르마펌", "스왓컷", "퀴프헤어", "언더컷", "투블럭 댄디컷", "네츄럴 댄디컷", "소프트 모히칸",
            "리젠트 헤어", "포마드 헤어", "스핀 스왈로펌", "댄디 섀도우펌"
    };
}

