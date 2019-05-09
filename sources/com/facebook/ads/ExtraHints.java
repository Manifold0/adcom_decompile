package com.facebook.ads;

import com.facebook.ads.internal.p025w.p026b.C2589s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class ExtraHints {
    /* renamed from: a */
    private final String f3743a;

    public static class Builder {
        /* renamed from: a */
        private HashMap<HintType, String> f3738a = new HashMap();

        public ExtraHints build() {
            return new ExtraHints(this.f3738a);
        }

        public Builder contentUrl(String str) {
            if (str != null) {
                this.f3738a.put(HintType.CONTENT_URL, str);
            }
            return this;
        }

        public Builder extraData(String str) {
            if (str != null) {
                this.f3738a.put(HintType.EXTRA_DATA, str);
            }
            return this;
        }

        public Builder keywords(List<Keyword> list) {
            if (!(list == null || list.isEmpty())) {
                List arrayList = new ArrayList();
                for (Keyword a : list) {
                    arrayList.add(a.f3742a);
                    if (arrayList.size() == 5) {
                        break;
                    }
                }
                this.f3738a.put(HintType.KEYWORDS, C2589s.m6656a(arrayList, ";"));
            }
            return this;
        }
    }

    public enum HintType {
        KEYWORDS("keywords"),
        CONTENT_URL("content_url"),
        EXTRA_DATA("extra_data");
        
        /* renamed from: a */
        private String f3740a;

        private HintType(String str) {
            this.f3740a = str;
        }
    }

    public enum Keyword {
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
        
        /* renamed from: a */
        private String f3742a;

        private Keyword(String str) {
            this.f3742a = str;
        }
    }

    private ExtraHints(HashMap<HintType, String> hashMap) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        for (Entry entry : hashMap.entrySet()) {
            try {
                jSONObject2.put(((HintType) entry.getKey()).f3740a, entry.getValue());
            } catch (JSONException e) {
            }
        }
        try {
            jSONObject.put("hints", jSONObject2);
        } catch (JSONException e2) {
        }
        this.f3743a = jSONObject.toString();
    }

    public String getHints() {
        return this.f3743a;
    }
}
