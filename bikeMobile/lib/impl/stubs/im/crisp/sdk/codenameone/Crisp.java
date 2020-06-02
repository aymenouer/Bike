package im.crisp.sdk.codenameone;


/**
 *  Bindings for Crisp SDK based on the crisp Android API
 * 
 *  @author Shai Almog
 */
public class Crisp {

	/**
	 *  @deprecated this API doesn't work properly in the JavaScript port. You should
	 *  use {@link #init(java.lang.String)} instead
	 */
	public static void init(String websiteId, com.codename1.ui.events.ActionListener onLoaded) {
	}

	public static Crisp getInstance() {
	}

	/**
	 *  @deprecated this API doesn't work properly in the JavaScript port. You should
	 *  use {@link #init(java.lang.String)} instead
	 */
	public void setTokenId(String tokenId) {
	}

	/**
	 *  @deprecated this API doesn't work properly in the JavaScript port. You should
	 *  use {@link #init(java.lang.String)} instead
	 */
	public String getTokenId() {
	}

	/**
	 *  Builder pattern for Crisp instances. Until build is invoked instance 
	 *  will be null.
	 */
	public static Crisp.Builder init(String websiteId) {
	}

	/**
	 *  @deprecated this API doesn't work properly in the JavaScript port. You should
	 *  use {@link #init(java.lang.String)} instead
	 */
	public void setEmail(String email) {
	}

	/**
	 *  @deprecated this API doesn't work properly in the JavaScript port. You should
	 *  use {@link #init(java.lang.String)} instead
	 */
	public void setNickname(String nickname) {
	}

	/**
	 *  @deprecated this API doesn't work properly in the JavaScript port. You should
	 *  use {@link #init(java.lang.String)} instead
	 */
	public void setPhone(String phone) {
	}

	/**
	 *  @deprecated this API doesn't work properly in the JavaScript port. You should
	 *  use {@link #init(java.lang.String)} instead
	 */
	public void setAvatar(String avatar) {
	}

	/**
	 *  @deprecated this API doesn't work properly in the JavaScript port. You should
	 *  use {@link #init(java.lang.String)} instead
	 */
	public void setData(String key, String value) {
	}

	/**
	 *  @deprecated this API doesn't work properly in the JavaScript port. You should
	 *  use {@link #init(java.lang.String)} instead
	 */
	public void setSegments(String segment) {
	}

	public void reset() {
	}

	public void openChat() {
	}

	public com.codename1.components.FloatingActionButton chatFab() {
	}

	public void bindFab(com.codename1.ui.Form f) {
	}

	/**
	 *  The builder class is used to create an instance of the crisp object in 
	 *  a portable way
	 */
	public static class Builder {


		public Builder() {
		}

		public Crisp.Builder websiteId(String websiteId) {
		}

		public Crisp.Builder email(String email) {
		}

		public Crisp.Builder phone(String phone) {
		}

		public Crisp.Builder nickname(String nickname) {
		}

		public Crisp.Builder avatar(String avatar) {
		}

		public Crisp build() {
		}
	}
}
