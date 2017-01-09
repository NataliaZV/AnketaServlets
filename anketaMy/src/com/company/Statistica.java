package com.company;

import java.util.HashMap;

public class Statistica {

    private HashMap <String, Integer> voiceAge = new HashMap<>();
    private HashMap <String, Integer> voiceCity = new HashMap<>();
    private HashMap <String, Integer> voiceLang = new HashMap<>();
    private HashMap <String, Integer> voiceLangCount = new HashMap<>();
    private HashMap <String, Integer> voiceExp = new HashMap<>();
    private HashMap <String, Integer> voiceEng = new HashMap<>();
    private static Statistica stat = new Statistica();

    private Statistica() {
        super();


    } public static Statistica getInstance() {
        return stat;
    }

    public HashMap<String, Integer> getVoiceAge() {
        return voiceAge;
    }

    public void setVoiceAge(HashMap<String, Integer> voiceAge) {
        this.voiceAge = voiceAge;
    }

    public HashMap<String, Integer> getVoiceExp() {
        return voiceExp;
    }

    public void setVoiceExp(HashMap<String, Integer> voiceExp) {
        this.voiceExp = voiceExp;
    }

    public HashMap<String, Integer> getVoiceCity() {
        return voiceCity;
    }

    public void setVoiceCity(HashMap<String, Integer> voiceCity) {
        this.voiceCity = voiceCity;
    }

    public HashMap<String, Integer> getVoiceLang() {
        return voiceLang;
    }

    public void setVoiceLang(HashMap<String, Integer> voiceLang) {
        this.voiceLang = voiceLang;
    }

    public HashMap<String, Integer> getVoiceLangCount() {
        return voiceLangCount;
    }

    public void setVoiceLangCount(HashMap<String, Integer> voiceLangCount) {
        this.voiceLangCount = voiceLangCount;
    }

    public HashMap<String, Integer> getVoiceEng() {
        return voiceEng;
    }

    public void setVoiceEng(HashMap<String, Integer> voiceEng) {
        this.voiceEng = voiceEng;
    }

    public synchronized void addVoice(String voice, HashMap <String, Integer> voiceMap) {
        if (voiceMap.containsKey(voice)) {
            Integer voiceCount = voiceMap.get(voice);
            voiceMap.put(voice, voiceCount + 1);
        } else {
            voiceMap.put(voice, 1);
        }
    }

    public synchronized void addVoiceCity (String voice, HashMap <String, Integer> voiceMap){
        String voiceNew;
        if (voice.equals("Kyiv")){
            voiceNew = "Kyiv";
        } else if (voice.equals("Dnipro")||voice.equals("Donetsk")||voice.equals("Lviv")||voice.equals("Odesa")||voice.equals("Kharkiv") ||voice.equals("Zaporizhzhia")) {
            voiceNew = "Large city (>700 ths)";
        } else {
            voiceNew = "Other city";
        }
        addVoice(voiceNew,voiceMap);
    }

    public synchronized void addVoiceLang (String [] voice, HashMap <String, Integer> voiceMap) {
        if (voice != null) {
            for (String text : voice) {
                addVoice(text, voiceMap);
            }
        } else {
            addVoice("no one", voiceMap);
        }
    }

    private synchronized String mapToSting(HashMap <String, Integer> voiceMap){
        StringBuilder sb = new StringBuilder();
        sb.append("<table bordercolor=#777c7c border=\"2\" cellpadding=\"3\">");
        voiceMap.forEach((key, value) ->
                sb.append("<tr><td width=\"170\" valign=\"top\"> ").
                        append(key).append("</td>").
                        append("<td width=\"40\" valign=\"top\" align=\"center\">").
                        append(value).append("</td></tr>"));
        sb.append("</table>");
        return sb.toString();
    }

    public synchronized String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Age</p>").append(mapToSting(voiceAge)).
                append("<p>City</p>").append(mapToSting(voiceCity)).
                append("<p>Language</p>").append(mapToSting(voiceLang)).
                append("<p>Number of languages</p>").append(mapToSting(voiceLangCount)).
                append("<p>Experience (years)</p>").append(mapToSting(voiceExp)).
                append("<p>English</p>").append(mapToSting(voiceEng));
        return sb.toString();
    }
}