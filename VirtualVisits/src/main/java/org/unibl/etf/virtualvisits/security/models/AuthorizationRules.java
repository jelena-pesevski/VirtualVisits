package org.unibl.etf.virtualvisits.security.models;

import java.util.List;

public class AuthorizationRules {

    private List<Rule> rules;

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
