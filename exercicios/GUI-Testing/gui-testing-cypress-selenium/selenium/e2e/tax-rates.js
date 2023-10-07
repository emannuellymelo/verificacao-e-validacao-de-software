const { Builder, By, until } = require('selenium-webdriver');
const assert = require('assert');

describe('tax rates', () => {
  let driver;

  before(async () => {
    driver = await new Builder().forBrowser('chrome').build();
  });

  after(async () => {
    await driver.quit();
  });

  beforeEach(async () => {
    driver.manage().deleteAllCookies();
    await driver.get('http://localhost:9990/admin');
    // await driver.get('http://150.165.75.99:9990/admin');
    await driver.findElement(By.id('_username')).sendKeys('sylius');
    await driver.findElement(By.id('_password')).sendKeys('sylius');
    // await driver.sleep(1000);
  });

  async function removeNewTaxRate() {
    await driver.findElement(By.id('criteria_search_value')).sendKeys('clothing_new_tax');
    await driver.findElement(By.css('*[class^="ui blue labeled icon button"]')).click();
    const deleteButtons = await driver.findElements(By.className('ui red labeled icon button'));
    await deleteButtons[deleteButtons.length - 1].click();
    await driver.findElement(By.id('confirmation-button')).click();
  }

  // Remove .only and implement others test cases!
  it.only('validate filter applied through cancel button', async () => {
    // Click on login button
    await driver.findElement(By.css('.primary')).click();
    
    // Click in tax rates in side menu
    await driver.findElement(By.linkText('Tax rates')).click();

    // Type in value input to search for specify tax rate
    await driver.findElement(By.id('criteria_search_value')).sendKeys('7');

    // Click in filter blue button
    await driver.findElement(By.css('*[class^="ui blue labeled icon button"]')).click();

    // Click in edit of the last tax rate
    const buttons = await driver.findElements(By.css('*[class^="ui labeled icon button "]'));
    await buttons[buttons.length - 1].click();

    // Click on cancel button
    await driver.findElement(By.css('.admin-layout__content > .ui > .ui > .ui > .ui:nth-child(2)')).click();

    // Assert that we are back to the listing page with the filter applied
    const bodyText = await driver.findElement(By.tagName('body')).getText();
    assert(bodyText.includes('Clothing Sales Tax 7%'));
    assert(!bodyText.includes('Sales Tax 20%'));
  }).timeout(10000);

  it('validate tax rate creation with success', async () => {
    await driver.findElement(By.css('.primary')).click();
    await driver.findElement(By.linkText('Tax rates')).click();

    // Click on create new tax button
    await driver.findElement(By.css('.right > .ui')).click();

    // Fill input fields
    await driver.findElement(By.id('sylius_tax_rate_code')).sendKeys('clothing_new_tax');
    await driver.findElement(By.id('sylius_tax_rate_name')).sendKeys('Clothing New Tax 5%');
    await driver.findElement(By.id('sylius_tax_rate_zone')).sendKeys('United States of America');
    await driver.findElement(By.id('sylius_tax_rate_amount')).clear();
    await driver.findElement(By.id('sylius_tax_rate_amount')).sendKeys('5');

    // Click on create
    const createButtons = await driver.findElements(By.className('ui labeled icon primary button'));
    await createButtons[createButtons.length - 1].click();

    // Go back to tax rates page
    await driver.findElement(By.linkText('Tax rates')).click();

    // Assert new tax on tax rates list
    const bodyText = await driver.findElement(By.tagName('body')).getText();
    assert(bodyText.includes('clothing_new_tax'));
    
    // Removing new tax to not influence on the other tests
    removeNewTaxRate()

  }).timeout(20000);

  it('validate tax rate zone edition with success', async () => {
    await driver.findElement(By.css('.primary')).click();
    await driver.findElement(By.linkText('Tax rates')).click();

    // Creating new tax for this test
    await driver.findElement(By.css('.right > .ui')).click();
    await driver.findElement(By.id('sylius_tax_rate_code')).sendKeys('clothing_new_tax');
    await driver.findElement(By.id('sylius_tax_rate_name')).sendKeys('Clothing New Tax 5%');
    await driver.findElement(By.id('sylius_tax_rate_zone')).sendKeys('United States of America');
    await driver.findElement(By.id('sylius_tax_rate_amount')).clear();
    await driver.findElement(By.id('sylius_tax_rate_amount')).sendKeys('5');
    const createButtons = await driver.findElements(By.className('ui labeled icon primary button'));
    await createButtons[createButtons.length-1].click();

    // Go back to tax rates page
    await driver.findElement(By.linkText('Tax rates')).click();

    // Click on edit tax button
    const buttons = await driver.findElements(By.css('*[class^="ui labeled icon button "]'));
    await buttons[buttons.length - 1].click();

    // Change selector for tax zone
    await driver.findElement(By.id('sylius_tax_rate_zone')).sendKeys('Rest of the World');

    // Click on update button
    const editButtons = await driver.findElements(By.className('ui labeled icon primary button'));
    await editButtons[editButtons.length-1].click();
    await driver.findElement(By.linkText('Tax rates')).click();

    // Assert update on tax rates list
    const bodyText = await driver.findElement(By.tagName('body')).getText();
    assert(bodyText.includes('Rest of the World'));
    removeNewTaxRate()
  }).timeout(20000);

  it('validate tax rates filtered by date', async () => {
    await driver.findElement(By.css('.primary')).click();
    await driver.findElement(By.linkText('Tax rates')).click();

    // Creating new tax for this test
    await driver.findElement(By.css('.right > .ui')).click();
    await driver.findElement(By.id('sylius_tax_rate_code')).sendKeys('clothing_new_tax');
    await driver.findElement(By.id('sylius_tax_rate_name')).sendKeys('Clothing New Tax 5%');
    await driver.findElement(By.id('sylius_tax_rate_zone')).sendKeys('United States of America');
    await driver.findElement(By.id('sylius_tax_rate_startDate_date')).sendKeys('01-10-2023');
    await driver.findElement(By.id('sylius_tax_rate_startDate_time')).sendKeys('10:00');
    await driver.findElement(By.id('sylius_tax_rate_endDate_date')).sendKeys('01-10-2024');
    await driver.findElement(By.id('sylius_tax_rate_endDate_time')).sendKeys('10:00');
    await driver.findElement(By.id('sylius_tax_rate_amount')).clear();
    await driver.findElement(By.id('sylius_tax_rate_amount')).sendKeys('5');
    const createButtons = await driver.findElements(By.className('ui labeled icon primary button'));
    await createButtons[createButtons.length-1].click();
    await driver.findElement(By.linkText('Tax rates')).click();

    // Adding filter by date
    await driver.findElement(By.id('criteria_startDate_from_date')).sendKeys('01-10-2023');
    await driver.findElement(By.id('criteria_startDate_from_time')).sendKeys('10:00');
    await driver.findElement(By.id('criteria_endDate_from_date')).sendKeys('01-10-2024');
    await driver.findElement(By.id('criteria_endDate_from_time')).sendKeys('10:00');
    await driver.findElement(By.css('*[class^="ui blue labeled icon button"]')).click();

    // Assert that only the new tax is shown
    const bodyText = await driver.findElement(By.tagName('body')).getText();
    assert(bodyText.includes('clothing_new_tax'));
    assert(!bodyText.includes('Sales Tax 20%'));
    assert(!bodyText.includes('Clothing Sales Tax 7%'));
    removeNewTaxRate()
  }).timeout(20000);

  it('validate tax rate list after filter cleaning', async () => {
    await driver.findElement(By.css('.primary')).click();
    await driver.findElement(By.linkText('Tax rates')).click();

     // Type in value input to search for specify tax rate
     await driver.findElement(By.id('criteria_search_value')).sendKeys('20');
     await driver.findElement(By.css('*[class^="ui blue labeled icon button"]')).click();

     // Cleaning filter by value
     await driver.findElement(By.linkText('Clear filters')).click();

     // Assert that all values are back to tax rates list
     const updatedBodyText = await driver.findElement(By.tagName('body')).getText();
     assert(updatedBodyText.includes('clothing_sales_tax_7'));
     assert(updatedBodyText.includes('sales_tax_20'));
  }).timeout(10000);

});
