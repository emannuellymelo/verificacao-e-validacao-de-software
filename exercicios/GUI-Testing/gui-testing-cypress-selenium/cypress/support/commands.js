Cypress.Commands.add('login', (username, password) => {
  cy.visit('/admin');
  cy.get('[id="_username"]').type(username);
  cy.get('[id="_password"]').type(password);
  cy.get('.primary').click();
});

Cypress.Commands.add('clickInFirst', (element) => {
  cy.get(element).first().scrollIntoView().click();
});

Cypress.Commands.add('createTaxRate', () => {
    cy.get('.right > .ui').click();
    cy.get('#sylius_tax_rate_code').type('clothing_new_tax');
    cy.get('#sylius_tax_rate_name').type('Clothing New Tax 5%');
    cy.get('#sylius_tax_rate_zone').select('United States of America');
    cy.get('#sylius_tax_rate_startDate_date').type('2023-10-01');
    cy.get('#sylius_tax_rate_startDate_time').type('10:00');
    cy.get('#sylius_tax_rate_endDate_date').type('2024-10-01');
    cy.get('#sylius_tax_rate_endDate_time').type('10:00');
    cy.get('#sylius_tax_rate_amount').clear().type('5');
    cy.get(':nth-child(4) > .ui > .required').click();
    cy.get('.buttons > .labeled').click();
    cy.get('.breadcrumb > [href="/admin/tax-rates/"]').click();
});

Cypress.Commands.add('removeNewTaxRate', () => {
  cy.get(':nth-child(1) > :nth-child(7) > .buttons > form > .ui').click();
  cy.get('#confirmation-button').click();
})
