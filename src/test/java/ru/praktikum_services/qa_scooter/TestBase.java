package ru.praktikum_services.qa_scooter;

import io.restassured.RestAssured;
import org.junit.Before;

public class TestBase {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        RestAssured.basePath ="/api/v1";
    }
}
