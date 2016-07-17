package hu.jegyertekesito.model;

import java.util.Date;

public class Felhasznalo {

	private int felhasznaloId;
	private String felhasznalonev;
	private String jelszo;
	private Date szulido;
	private String keresztnev;
	private String vezeteknev;
	private String email;
	private String lakhely;
	private String utca;
	private int telefonszam;
	private boolean neme;
	
	
	public Felhasznalo(){
		
	}
	
	public Felhasznalo(int felhasznaloId, String felhasznalonev, String jelszo, Date szulido, String 

keresztnev, String vezeteknev, String email, String lakhely, String utca, int telefonszam, boolean 

neme){
		this.felhasznaloId=felhasznaloId;
		this.felhasznalonev=felhasznalonev;
		this.jelszo=jelszo;
		this.szulido=szulido;
		this.keresztnev=keresztnev;
		this.vezeteknev=vezeteknev;
		this.email=email;
		this.lakhely=lakhely;
		this.utca=utca;
		this.telefonszam=telefonszam;
		this.neme=neme;
	}
	
	public int getFelhasznaloID(){
		return felhasznaloId;
	}
	
	public void setFelhasznaloID(int felhasznaloId){
		this.felhasznaloId=felhasznaloId;
	}
	public String getFelhasznalonev() {
		return felhasznalonev;
	}

	public void setFelhasznalonev(String felhasznalonev) {
		this.felhasznalonev = felhasznalonev;
	}
	
	public String getJelszo() {
		return jelszo;
	}
	public void setJelszo(String jelszo) {
		this.jelszo = jelszo;
	}

	public Date getSzulido() {
		return szulido;
	}

	public void setSzulido(Date szulido) {
		this.szulido = szulido;
	}

	public String getKeresztnev() {
		return keresztnev;
	}

	public void setKeresztnev(String keresztnev) {
		this.keresztnev = keresztnev;
	}

	public String getVezeteknev() {
		return vezeteknev;
	}

	public void setVezeteknev(String vezeteknev) {
		this.vezeteknev = vezeteknev;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLakhely() {
		return lakhely;
	}

	public void setLakhely(String lakhely) {
		this.lakhely = lakhely;
	}

	public String getUtca() {
		return utca;
	}

	public void setUtca(String utca) {
		this.utca = utca;
	}

	public int getTelefonszam() {
		return telefonszam;
	}

	public void setTelefonszam(int telefonszam) {
		this.telefonszam = telefonszam;
	}

	public boolean isNeme() {
		return neme;
	}

	public void setNeme(boolean neme) {
		this.neme = neme;
	}

	@Override
	public String toString(){
		return "Felhasznalo [" + "felhasznalonev=" + felhasznalonev + ", jelszo=" + jelszo + ", 

szuletesiido=" + szulido 
				+ ", keresztnev=" + keresztnev + ", vezeteknev=" + vezeteknev + ", 

email=" + email + ", lakhely=" + lakhely + ", utca=" + utca 
				+ ", telefonszam=" + telefonszam + ", neme=" + neme + "]";
	}
		
}

