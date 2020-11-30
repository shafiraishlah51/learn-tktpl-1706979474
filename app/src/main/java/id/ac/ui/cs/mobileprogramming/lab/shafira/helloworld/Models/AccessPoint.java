package id.ac.ui.cs.mobileprogramming.lab.shafira.helloworld.Models;

public class AccessPoint {
    private String name;
    private String capabilities;
    private String rrsi;
    private String frequency;
    private String bssid;

    public AccessPoint(String name, String mac, int strength) {
        this.name = name;
        this.capabilities = capabilities;
        this.rrsi = rrsi;
        this.frequency = frequency;
        this.bssid = bssid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public String getRrsi() {
        return rrsi;
    }

    public void setRrsi(String rrsi) {
        this.rrsi = rrsi;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }
}
