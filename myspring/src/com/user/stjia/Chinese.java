package com.user.stjia;


public class Chinese implements Person {

	private Axe axe;

	public void setAxe(Axe axe) {
		this.axe = axe;
	}

	@Override
	public void useAxe() {
		// TODO Auto-generated method stub
		String string = axe.chop();
		System.out.println(string);
	}

	@Override
	public void sayTime() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.user.stjia.Person#useAxe(java.lang.String)
	 */
	@Override
	public String useAxe(String use) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
