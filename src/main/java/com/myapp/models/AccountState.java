package com.myapp.models;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "StanKonta")
@XmlAccessorType(XmlAccessType.NONE)
public class AccountState {
    public String konto = "";
    public Number boSaldo = 0;
    public Number boWn = 0;
    public Number boMa = 0;

    public AccountState() {}

    public AccountState(String konto, Number boSaldo, Number boWn, Number boMa){
        this.konto = konto;
        this.boSaldo = boSaldo;
        this.boWn = boWn;
        this.boMa = boMa;
    }

    @XmlElement(name = "konto")
    public String getKonto(){
        return this.konto;
    }

    @XmlElement(name = "saldo")
    public String getBoSaldo() {
        return this.boSaldo.toString();
    }

    @XmlElement(name = "winien")
    public String getBoWn() {
        return this.boWn.toString();
    }

    @XmlElement(name = "ma")
    public String getBoMa() {
        return this.boMa.toString();
    }


    public AccountState(String konto) {
        this.konto = konto;
    }

    @Override
    public String toString() {
        return  "-------------------------------\n" +
                "KONTO: " + this.konto + "\n" +
                "Saldo: " + this.boSaldo + "\n" +
                "Winien: " + this.boWn + "\n" +
                "Ma: " + this.boMa + "\n";
    }

}
