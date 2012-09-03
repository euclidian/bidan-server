package org.ei.drishti.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

public class ActionData {
    private Map<String, String> data;
    private String target;
    private String type;
    private Map<String, String> details;

    public static ActionData createAlert(BeneficiaryType beneficiaryType, String visitCode, AlertPriority alertPriority, DateTime startDate, DateTime expiryDate) {
        return new ActionData("alert", "createAlert")
                .with("beneficiaryType", beneficiaryType.value())
                .with("visitCode", visitCode)
                .with("alertPriority", alertPriority.value())
                .with("startDate", startDate.toLocalDate().toString())
                .with("expiryDate", expiryDate.toLocalDate().toString());
    }

    public static ActionData markAlertAsClosed(String visitCode) {
        return new ActionData("alert", "closeAlert")
                .with("visitCode", visitCode);
    }

    public static ActionData deleteAllAlerts() {
        return new ActionData("alert", "deleteAllAlerts");
    }

    public static ActionData createEligibleCouple(String wife, String husband, String ecNumber, String village, String subCenter, String phc, Map<String, String> details) {
        return new ActionData("eligibleCouple", "createEC")
                .with("wife", wife)
                .with("husband", husband)
                .with("ecNumber", ecNumber)
                .with("village", village)
                .with("subcenter", subCenter)
                .with("phc", phc)
                .withDetails(details);
    }

    public static ActionData deleteEligibleCouple() {
        return new ActionData("eligibleCouple", "deleteEC");
    }

    public static ActionData registerPregnancy(String ecCaseId, String thaayiCardNumber, LocalDate lmpDate, Map<String, String> details) {
        return new ActionData("mother", "registerPregnancy")
                .with("ecCaseId", ecCaseId)
                .with("thaayiCardNumber", thaayiCardNumber)
                .with("status", "pregnant")
                .with("referenceDate", lmpDate.toString())
                .withDetails(details);
    }

    public static ActionData closeANC(String reasonForClose) {
        return new ActionData("mother", "closeANC")
                .with("reasonForClose", reasonForClose);
    }

    public static ActionData registerChildBirth(String motherCaseId, LocalDate dateOfBirth, String gender) {
        return new ActionData("child", "register")
                .with("motherCaseId", motherCaseId)
                .with("referenceDate", dateOfBirth.toString())
                .with("gender", gender);
    }

    public static ActionData updateEligibleCoupleDetails(Map<String, String> details) {
        return new ActionData("eligibleCouple", "updateDetails").
                withDetails(details);
    }

    public static ActionData updateMotherDetails(Map<String, String> details) {
        return new ActionData("mother", "updateDetails").
                withDetails(details);
    }

    public static ActionData ancCareProvided(int visitNumber, LocalDate visitDate, int numberOfIFATabletsProvided) {
            return new ActionData("mother", "ancCareProvided")
                .with("visitNumber", String.valueOf(visitNumber))
                .with("visitDate", visitDate.toString())
                .with("numberOfIFATabletsProvided", String.valueOf(numberOfIFATabletsProvided));
    }

    public static ActionData from(String actionType, String actionTarget, Map<String, String> data, Map<String, String> details) {
        ActionData actionData = new ActionData(actionTarget, actionType);
        actionData.data.putAll(data);
        actionData.details.putAll(details);
        return actionData;
    }

    private ActionData(String target, String type) {
        this.target = target;
        this.type = type;
        data = new HashMap<String, String>();
        details = new HashMap<String, String>();
    }

    private ActionData with(String key, String value) {
        data.put(key, value);
        return this;
    }

    private ActionData withDetails(Map<String, String> details) {
        this.details.putAll(details);
        return this;
    }

    public Map<String, String> data() {
        return data;
    }

    public String target() {
        return target;
    }

    public String type() {
        return type;
    }

    public Map<String, String> details() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}