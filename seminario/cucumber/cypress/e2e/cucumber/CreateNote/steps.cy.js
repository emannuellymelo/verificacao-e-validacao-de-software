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

When(/^digito na barra de pesquisa "([^"]*)"$/, (args1) => {
	cy.get('[data-testid="note-search"]').type(args1);
});

Then(/^minha nota "([^"]*)" deve estar exibida na listagem$/, (args1) => {
	cy.get('[data-testid="note-list-item-0"]').should('contain', args1);
});

Then(/^minha nota "([^"]*)" vai estar "([^"]*)" na listagem$/, (args1, args2) => {
	if(args2 == 'presente') {
        cy.get('[data-testid="note-list-item-0"]').should('contain', args1);
    } else {
        cy.get('[data-testid="note-list-item-0"]').should('not.contain', args1);
    }
});
