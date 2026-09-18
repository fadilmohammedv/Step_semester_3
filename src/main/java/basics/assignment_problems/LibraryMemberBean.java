public class LibraryMemberBean {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMemberBean() {
        membershipId = "";
        name = "";
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        if (this.membershipId.isEmpty()) {
            this.membershipId = membershipId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    public void setSecurityAnswer(String answer) {
        securityAnswer = answer == null ? null : Integer.toHexString(answer.hashCode());
    }

    public static void main(String[] args) {
        LibraryMemberBean member = new LibraryMemberBean();
        member.setMembershipId("LIB-8841");
        member.setMembershipId("FAKE-0000");
        member.setName("Priya Nair");
        member.setPremiumMember(true);
        member.setSecurityAnswer("BlueMountain");
        System.out.println(member.getMembershipId());
        System.out.println(member.isPremiumMember());
    }
}