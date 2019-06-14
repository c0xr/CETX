package com.csti.cetx.bmob;

import cn.bmob.v3.BmobObject;

public class Vocabulary extends BmobObject {

    private String spell;
    private String paraphrase;
    private boolean isMemory;

    public Vocabulary(){
        isMemory = false;
    }

    public Vocabulary(String spell, String paraphrase){
        this.spell = spell;
        this.paraphrase = paraphrase;
        isMemory = false;
    }

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

    public boolean isMemory() {
        return isMemory;
    }

    public void setMemory(boolean memory) {
        isMemory = memory;
    }
}
