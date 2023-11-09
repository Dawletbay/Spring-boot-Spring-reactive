package uz.best.company.enums;

public enum LanguageEnum {
    uz,
    en,
    ru;

    public static LanguageEnum getDefaultLanguage() {
        return LanguageEnum.ru;
    }
}
