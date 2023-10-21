import {Given, When, Then} from "cypress-cucumber-preprocessor/steps"

Given(/^que eu acesso o site da aplicacao TakeNote$/, () => {
	cy.visit("https://takenote.dev/app")
});

When(/^clico em criar uma nota$/, () => {
    cy.get('[data-testid="sidebar-action-create-new-note"]').click();
});

When(/^digito "([^"]*)"$/, (args1) => {
	cy.get('.CodeMirror-line').type(args1);
});

When(/^clico nas opções da nota (\d+)$/, (num) => {
    cy.get(`[data-testid="note-options-div-${num}"]`).click();
});

When(/^clico na opção de favoritos$/, () => {
    cy.get(`[data-testid="note-option-favorite"]`).click();
});

When(/^verifico a lista de favoritos$/, () => {
    cy.get(`[data-testid="favorites"]`).click();
});

Then(/^minha nota "([^"]*)" deve estar exibida na listagem de favoritos$/, (args1) => {
	cy.get('[data-testid="note-list"]').should('contain', args1);
});

Then(/^minha nota "([^"]*)" vai estar "([^"]*)" na listagem de favoritos$/, (args1, args2) => {
	if(args2 == 'presente') {
        cy.get('[data-testid="note-list"]').should('contain', args1);
    } else {
        cy.get('[data-testid="note-list"]').should('not.contain', args1);
    }
});


