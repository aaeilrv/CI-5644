/// <reference types="cypress" />

describe("Create New Account", () => {
  before(() => {
    cy.visit("http://localhost:3000");
    cy.get("#home");
    cy.contains("Regístrate").click();

    cy.origin("https://dev-crxb1uekqycez2b5.us.auth0.com/login", () => {
      cy.get("#1-email").type("finamore03@protonmail.com");
      cy.get("#1-password").type("Santoigo16*");
      cy.get("#1-submit").click();
    });

    cy.viewport(1280, 720);
    
  });

  it("Get to Profile", () => {
    cy.get('[data-testid="Perfil"]').click();

    cy.url().as("profileURL");
    cy.get("@profileURL").should("include", "localhost:3000/profile");
  });
});
