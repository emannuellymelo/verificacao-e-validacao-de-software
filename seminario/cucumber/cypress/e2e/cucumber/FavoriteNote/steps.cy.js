import {Given, When, Then} from "cypress-cucumber-preprocessor/steps"

When(/^clico nas opções da primeira nota$/, (num) => {
    cy.get(`[data-testid="note-options-div-0"]`).click();
});

When(/^clico na opção de favoritos$/, () => {
    cy.get(`[data-testid="note-option-favorite"]`).click();
});

When(/^vou para a aba de favoritos$/, () => {
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


