package ua.kiev.supersergey.deputysearch.commonlib.filter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by supersergey on 20.05.18.
 */
public class CompanyNameTransformerTest {
    @Test
    public void transform() throws Exception {
        assertEquals("БІКОМ", CompanyNameTransformer.transform("ТОВ \"СД \"БІКОМ\""));
        assertEquals("БІКОМ", CompanyNameTransformer.transform("БІКОМ"));
        assertEquals("Нова лінія-Запоріжжя", CompanyNameTransformer.transform("Нова лінія-Запоріжжя"));
        assertEquals("БІЛОЦЕРКІВІНВЕСТБУД ХОЛДИНГ", CompanyNameTransformer.transform("Приватне підприємство \"БІЛОЦЕРКІВІНВЕСТБУД ХОЛДИНГ\""));
        assertEquals("ОЛВІ", CompanyNameTransformer.transform("Страхова компанія товариство з додатковою відповідальністю ''ОЛВІ''"));
        assertEquals("abc", CompanyNameTransformer.transform("abc"));
        assertEquals("ТОН", CompanyNameTransformer.transform("ТОВ \"ТОН\""));
        assertEquals("Замок Радомисль", CompanyNameTransformer.transform("Історико-культурний комплекс \"Замок Радомисль\""));
        assertEquals("Новембе Венчез", CompanyNameTransformer.transform("Новембе Венчез Лтд"));
        assertEquals("Брокер Фаворит", CompanyNameTransformer.transform("ТОВ \"Брокер Фаворит\""));
        assertEquals("ЛИСИЧАНСЬКИЙ СКЛЯНИЙ ЗАВОД", CompanyNameTransformer.transform("ТОВ \"ЛИСИЧАНСЬКИЙ СКЛЯНИЙ ЗАВОД\""));
        assertEquals("Вінницька промислова група", CompanyNameTransformer.transform("СХК Вінницька промислова група"));
        assertEquals("ПРОЛЕТАРІЙ", CompanyNameTransformer.transform("ТОВ \"ТОРГОВИЙ ДІМ \"ПРОЛЕТАРІЙ\""));
        assertEquals("Кліринговий Дім", CompanyNameTransformer.transform("АБ \"Кліринговий Дім\""));
        assertEquals("ЕВРОІНВЕСТ ПЛЮС", CompanyNameTransformer.transform("ТОВ \"КУА \"ЕВРОІНВЕСТ ПЛЮС\""));
        assertEquals("ЛИДЛЕНД", CompanyNameTransformer.transform("ЛИДЛЕНД ЛТД"));
        assertEquals("ЛИДЛЕНД", CompanyNameTransformer.transform("ЛИДЛЕНД лтд."));
        assertEquals("ЛИДЛЕНД", CompanyNameTransformer.transform("ТзОВ ЛИДЛЕНД лтд"));
        assertEquals("ЛИДЛЕНД", CompanyNameTransformer.transform("ТзОВ ЛИДЛЕНД ЛімІТЕд."));
    }

}