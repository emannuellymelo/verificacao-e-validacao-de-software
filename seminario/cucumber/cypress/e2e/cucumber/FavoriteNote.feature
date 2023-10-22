Feature: FavoriteNote

    Como usuario, desejo poder adicionar ou remover uma nota na lista de favoritos
    e poder visualizar as notas favoritadas.

Background:
    Given que eu acesso o site da aplicacao TakeNote
    And clico em criar uma nota
    And digito "Minha Nota"
    And clico nas opções da primeira nota
    And clico na opção de favoritos
    And vou para a aba de favoritos

Scenario: Adicionar uma nota aos favoritos
    Then minha nota "Minha Nota" deve estar exibida na listagem de favoritos

Scenario: Adicionar e remover uma nota dos favoritos
    And clico nas opções da primeira nota
    And clico na opção de favoritos
    Then minha nota "Minha Nota" não deve estar exibida na listagem de favoritos