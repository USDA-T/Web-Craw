package gov.sba.utils.helpers;

public class LoginData {

    public String email;
    public String password;
    public String dunsNumber;
    public String taxIdentifier;
    public String miscInfo;
    public String businessType;

    public LoginData() {
    }

    public LoginData(String email, String password, String dunsNumber, String taxIdentifier, String miscInfo,
            String businessType) {
        super();
        this.email = email;
        this.password = password;
        this.dunsNumber = dunsNumber;
        this.taxIdentifier = taxIdentifier;
        this.miscInfo = miscInfo;
        this.businessType = businessType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDunsNumber() {
        return dunsNumber;
    }

    public void setDunsNumber(String dunsNumber) {
        this.dunsNumber = dunsNumber;
    }

    public String getTaxIdentifier() {
        return taxIdentifier;
    }

    public void setTaxIdentifier(String taxIdentifier) {
        this.taxIdentifier = taxIdentifier;
    }

    public String getMiscInfo() {
        return miscInfo;
    }

    public void setMiscInfo(String miscInfo) {
        this.miscInfo = miscInfo;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Override
    public String toString() {
        return "LoginData [email=" + email + ", password=" + password + ", dunsNumber=" + dunsNumber
                + ", taxIdentifier=" + taxIdentifier + ", miscInfo=" + miscInfo + ", businessType=" + businessType
                + "]";
    }
}
