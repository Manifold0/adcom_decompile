// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import java.util.Iterator;
import com.facebook.ads.internal.w.b.s;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import java.util.Map;
import org.json.JSONObject;
import java.util.HashMap;

public class ExtraHints
{
    private final String a;
    
    private ExtraHints(HashMap<HintType, String> iterator) {
        final JSONObject jsonObject = new JSONObject();
        final JSONObject jsonObject2 = new JSONObject();
        iterator = ((HashMap<Object, Object>)iterator).entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<Object, Object> entry = iterator.next();
            try {
                jsonObject2.put(entry.getKey().a, entry.getValue());
            }
            catch (JSONException ex) {}
        }
        while (true) {
            try {
                jsonObject.put("hints", (Object)jsonObject2);
                this.a = jsonObject.toString();
            }
            catch (JSONException ex2) {
                continue;
            }
            break;
        }
    }
    
    public String getHints() {
        return this.a;
    }
    
    public static class Builder
    {
        private HashMap<HintType, String> a;
        
        public Builder() {
            this.a = new HashMap<HintType, String>();
        }
        
        public ExtraHints build() {
            return new ExtraHints(this.a, null);
        }
        
        public Builder contentUrl(final String s) {
            if (s == null) {
                return this;
            }
            this.a.put(HintType.CONTENT_URL, s);
            return this;
        }
        
        public Builder extraData(final String s) {
            if (s == null) {
                return this;
            }
            this.a.put(HintType.EXTRA_DATA, s);
            return this;
        }
        
        public Builder keywords(final List<Keyword> list) {
            if (list == null || list.isEmpty()) {
                return this;
            }
            final ArrayList<String> list2 = new ArrayList<String>();
            final Iterator<Keyword> iterator = list.iterator();
            while (iterator.hasNext()) {
                list2.add(iterator.next().a);
                if (list2.size() == 5) {
                    break;
                }
            }
            this.a.put(HintType.KEYWORDS, s.a(list2, ";"));
            return this;
        }
    }
    
    public enum HintType
    {
        CONTENT_URL("content_url"), 
        EXTRA_DATA("extra_data"), 
        KEYWORDS("keywords");
        
        private String a;
        
        private HintType(final String a) {
            this.a = a;
        }
    }
    
    public enum Keyword
    {
        ACCESSORIES("accessories"), 
        ART_HISTORY("art_history"), 
        AUTOMOTIVE("automotive"), 
        BEAUTY("beauty"), 
        BIOLOGY("biology"), 
        BOARD_GAMES("board_games"), 
        BUSINESS_SOFTWARE("business_software"), 
        BUYING_SELLING_HOMES("buying_selling_homes"), 
        CATS("cats"), 
        CELEBRITIES("celebrities"), 
        CLOTHING("clothing"), 
        COMIC_BOOKS("comic_books"), 
        DESKTOP_VIDEO("desktop_video"), 
        DOGS("dogs"), 
        EDUCATION("education"), 
        EMAIL("email"), 
        ENTERTAINMENT("entertainment"), 
        FAMILY_PARENTING("family_parenting"), 
        FASHION("fashion"), 
        FINE_ART("fine_art"), 
        FOOD_DRINK("food_drink"), 
        FRENCH_CUISINE("french_cuisine"), 
        GOVERNMENT("government"), 
        HEALTH_FITNESS("health_fitness"), 
        HOBBIES("hobbies"), 
        HOME_GARDEN("home_garden"), 
        HUMOR("humor"), 
        INTERNET_TECHNOLOGY("internet_technology"), 
        LARGE_ANIMALS("large_animals"), 
        LAW("law"), 
        LEGAL_ISSUES("legal_issues"), 
        LITERATURE("literature"), 
        MARKETING("marketing"), 
        MOVIES("movies"), 
        MUSIC("music"), 
        NEWS("news"), 
        PERSONAL_FINANCE("personal_finance"), 
        PETS("pets"), 
        PHOTOGRAPHY("photography"), 
        POLITICS("politics"), 
        REAL_ESTATE("real_estate"), 
        ROLEPLAYING_GAMES("roleplaying_games"), 
        SCIENCE("science"), 
        SHOPPING("shopping"), 
        SOCIETY("society"), 
        SPORTS("sports"), 
        TECHNOLOGY("technology"), 
        TELEVISION("television"), 
        TRAVEL("travel"), 
        VIDEO_COMPUTER_GAMES("video_computer_games");
        
        private String a;
        
        private Keyword(final String a) {
            this.a = a;
        }
    }
}
