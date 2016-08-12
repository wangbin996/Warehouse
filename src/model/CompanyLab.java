package model;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CompanyLab {

	private ArrayList<Company> mCompanys;
	private static CompanyLab sCompany;
	
	private CompanyLab(Context context){
		if(mCompanys == null){
			mCompanys = new ArrayList<Company>();
		}
	}

	public static CompanyLab get(Context context){
		if(sCompany == null){
			sCompany = new CompanyLab(context);
		}
		return sCompany;
	}
	
	public ArrayList<Company> getCompanys(){
		return mCompanys;
	}
	
	public Company getCompany(UUID id){
		for(Company company:mCompanys){
			if(company.getId().equals(id)){
				return company;
			}
		}
		return null;
	}
	
	public void addCompany(Company company){
		mCompanys.add(company);
	}
	
	public void deleteCompany(Company company){
		int i=0,j=0;
		for(i=0; i<mCompanys.size(); i++){
			if(mCompanys.get(i).getId().equals(company.getId())){
		//		Log.d("wangbin", "一样就显示"+i);
				j=i;
			}
		}
		mCompanys.remove(j);
	}
	
}
