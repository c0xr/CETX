package com.csti.cetx.bmob;

import cn.bmob.v3.BmobObject;

public class Vocabulary extends BmobObject {

    private String spell;
    private String paraphrase;

    public Vocabulary(){ }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getParaphrase() {
        return paraphrase;
    }

    public void setParaphrase(String paraphrase) {
        this.paraphrase = paraphrase;
    }
}
