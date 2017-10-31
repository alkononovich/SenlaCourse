package config;

public class Props {
	private boolean toggleOnCompleteClaim;
	private int oldMonth;
	private String serialPath;

	public boolean isToggleOnCompleteClaim() {
		return toggleOnCompleteClaim;
	}

	public void setToggleOnCompleteClaim(boolean toggleOnCompleteClaim) {
		this.toggleOnCompleteClaim = toggleOnCompleteClaim;
	}

	public int getOldMonth() {
		return oldMonth;
	}

	public void setOldMonth(int oldMonth) {
		this.oldMonth = oldMonth;
	}

	public String getSerialPath() {
		return serialPath;
	}

	public void setSerialPath(String serialPath) {
		this.serialPath = serialPath;
	}
	
	
}
