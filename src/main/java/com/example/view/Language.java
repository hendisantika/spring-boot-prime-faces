package com.example.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ManagedBean(name = "language")
@SessionScoped
@Controller
public class Language {

	@Autowired
	private MessageSource messageSource;

	private Locale locale;

	Map<String, Locale> languages = new HashMap<String, Locale>();

	private String localeCode;

	public Language() {
		super();
		languages.put("en", Locale.ENGLISH);
		languages.put("es", new Locale("es"));
		this.localeCode = "en";
		this.locale = new Locale("en");
		FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
	}

	public String getMessage(String property) {
		String message = messageSource.getMessage(property, null, this.locale);
		return message;
	}

	public void countryLocaleCodeChanged(String language) {
		for (Map.Entry<String, Locale> entry : languages.entrySet()) {
			if (entry.getValue().toString().equals(language)) {
				this.locale = entry.getValue();
				this.localeCode = entry.getKey();
				FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
			}
		}
	}

	public Map<String, Locale> getLanguages() {
		return languages;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

}
