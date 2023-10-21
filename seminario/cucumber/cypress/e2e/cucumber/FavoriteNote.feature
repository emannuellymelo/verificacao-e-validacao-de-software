Feature: FavoriteNote

    Como usuario, desejo poder adicionar ou remover uma nota na lista de favoritos
    e poder visualizar as notas favoritadas.

Scenario Outline: Adicionar uma nota aos favoritos
    Given que eu acesso o site da aplicacao TakeNote
    And clico em criar uma nota
    When digito "<note>"
    And clico nas opções da nota 0
    And clico na opção de favoritos
    And verifico a lista de favoritos
    Then minha nota "<note>" deve estar exibida na listagem de favoritos

Examples:
 | note     |
 | Minha Nota |

Scenario Outline: Adicionar e remover uma nota dos favoritos
    Given que eu acesso o site da aplicacao TakeNote
    And clico em criar uma nota
    When digito "<note>"
    And clico nas opções da nota 0
    And clico na opção de favoritos
    And verifico a lista de favoritos
    And clico nas opções da nota 0
    And clico na opção de favoritos
    Then minha nota "<note>" vai estar "<presenca>" na listagem de favoritos

Examples:
 | note     | presenca |
 | Minha Nota | ausente |