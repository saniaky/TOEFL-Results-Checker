package ets;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Date;

/**
 * @author Alexander Kohonovsky
 * @since 10.10.2017
 */
public class ETSParser {

    private static final String LOGIN_PAGE = "https://toefl-registration.ets.org/TOEFLWeb/logon.do";
    private static final String SCORES_PAGE = "https://toefl-registration.ets.org/TOEFLWeb/profile/toModule.do?prefix=/reportmanagement&page=/viewScores.do";
    private static final String JSESSIONID = "JSESSIONID";

    private String cookie = "";

    public Status parse() {
        try {
            if (!login()) {
                return Status.ERROR;
            }

            Connection.Response response = Jsoup
                    .connect(SCORES_PAGE)
                    .followRedirects(false)
                    .cookie(JSESSIONID, cookie)
                    .execute();

            if (response.statusCode() == 302) {
                return Status.ERROR;
            }

            String text = response.parse().select("td[width='19%']").get(1).text();

            if ("Not Available".equals(text)) {
                System.out.println(new Date().toString() + " - Not available...");
                return Status.NO_RESULTS;
            }

            if ("Available".equals(text)) {
                return Status.RESULTS_AVAILABLE;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Status.ERROR;
    }

    private boolean login() {
        boolean isSuccessful = false;

        try {
            Connection.Response response = Jsoup
                    .connect(LOGIN_PAGE)
                    .method(Connection.Method.POST)
                    .data("username", "")
                    .data("password", "")
                    .data("currentLocale", "en_US")
                    .followRedirects(false)
                    .execute();

            if (response.statusCode() == 302) {
                cookie = response.cookie(JSESSIONID);
                isSuccessful = true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSuccessful;
    }
}
