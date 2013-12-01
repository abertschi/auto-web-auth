package org.autowebauth.client.fx.mvcprovider;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Callback;

/**
 * This concept is based on a tutorial by Adam Bien.
 * 
 * <pre>
 * conventions for subject 'setting'
 * conventional-name: setting
 * name of view: SettingView
 * name of presenter: SettingPresenter
 * name of fxml: setting.fxml
 * name of css: setting.css
 * 
 * <pre>
 * 
 * @see www.adam-bien.com
 * 
 * @author abertschi
 * @since 01.12.2013
 * 
 */
public abstract class FXMLView {

	public static final String DEFAULT_SUFFIX = "view";

	protected FXMLLoader loader;

	public FXMLView() {
		this.init(getClass(), getFXMLName());
	}

	private void init(Class<? extends FXMLView> clazz, String fxmlName) {
		this.loader = new FXMLLoader();
		this.loader.setControllerFactory(new Callback<Class<?>, Object>() {
			@Override
			public Object call(Class<?> bean) {
				return ManagedBeanLocator.getInstance().lookUp(bean);
			}
		});
		try {
			this.loader.load();
		} catch (IOException e) {
			throw new IllegalStateException("Not able to create " + fxmlName);
		}
	}

	String getConventionalName() {
		// f.e. settingview
		String className = this.getClass().getSimpleName().toLowerCase();
		// remove view suffix
		return removeSuffix(className);
	}

	String getConventionalName(String suffix) {
		return getConventionalName() + suffix;
	}

	String removeSuffix(String clazz) {
		if (!clazz.endsWith(DEFAULT_SUFFIX)) {
			return clazz;
		}
		int endIndex = clazz.lastIndexOf(DEFAULT_SUFFIX);
		return clazz.substring(0, endIndex);
	}

	public Parent getView() {
		Parent p = this.loader.getRoot();
		// add CSS?
		return p;
	}

	public Object getPresenter() {
		return this.loader.getController();
	}

	public String getFXMLName() {
		return getConventionalName(".fxml");
	}

	public String getCSSName() {
		return getConventionalName(".css");
	}

}
