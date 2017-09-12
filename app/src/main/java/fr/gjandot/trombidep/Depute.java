package fr.gjandot.trombidep;

import java.util.Comparator;

public class Depute {
	private String nom;
	private String prenom;
	private String imgurl;
	private String depurl;
	private String circo;
	private String numdpt;
	private String grp;
	private boolean sexe_H = true;


	public Depute(Depute dep)
	{
		super();
		this.nom = dep.nom;
		this.imgurl = dep.imgurl;
		this.depurl = dep.depurl;
		this.grp = dep.grp;
		this.sexe_H = dep.sexe_H;
		this.circo = dep.circo;
		this.numdpt = dep.numdpt;
	}

	public Depute()
	{
		super();
	}

	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
	
	public String getImgUrl() { return this.imgurl; }
	public void setImgUrl(String imgurl) { this.imgurl = imgurl; }
	
	public String getDepUrl() { return this.depurl;	}
	public void setDepUrl(String depurl) { this.depurl = depurl; }
	
	public String getGrp() { return this.grp; }
	public void setGrp(String grp) { this.grp = grp; }
	
	public String getCirco() { return this.circo; }
	public void setCirco(String circo) { this.circo = circo; }

	public String getNumDpt() { return this.numdpt; }
	public void setNumDpt(String numdpt) { this.numdpt = numdpt; }

	public String getLongCirco() { return this.circo + " (" + this.getNumDpt() + ")"; }

	public void setSexe_H(boolean sexe_h) { this.sexe_H = sexe_h;}
	public boolean isSexe_H() { return this.sexe_H; }

	static class DepComparateur implements Comparator<Object> {
		    public int compare(Object o1, Object o2) {
		      if (!(o1 instanceof Depute) || !(o2 instanceof Depute))
		        throw new ClassCastException();

				Depute s1 = (Depute) o1;
				Depute s2 = (Depute) o2;

		      return s1.getNom().compareTo(s2.getNom());
		    }
	}
}
