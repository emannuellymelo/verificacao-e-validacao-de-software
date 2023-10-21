Feature: CreateNote

    Como usuario, desejo criar uma nota
    Para que eu possa conferir futuramente minhas anotacoes

# Forma simples de criar um cenario inserindo dados um a um
Scenario: Criar uma nota valida
    Given que eu acesso o site da aplicacao TakeNote
    And clico em criar uma nota
    When digito "1st Note"
    And digito na barra de pesquisa "1st Note"
    Then minha nota "1st Note" deve estar exibida na listagem

# Criando esquema de cenario usando variavel
Scenario Outline: Criar uma nota valida
    Given que eu acesso o site da aplicacao TakeNote
    And clico em criar uma nota
    When digito "<note>"
    And digito na barra de pesquisa "<note>"
    Then minha nota "<note>" deve estar exibida na listagem

Examples:
 | note     |
 | 1st Note |
 | 2nd Note |

# Esquema de cenario para anotacoes validas e invalidas
Scenario Outline: Criar uma nota <tipoDeNota>
    Given que eu acesso o site da aplicacao TakeNote
    And clico em criar uma nota
    When digito <note>
    And digito na barra de pesquisa <note>
    Then minha nota <note> vai estar "<presenca>" na listagem

Examples:
 | tipoDeNota | note       | presenca |
 | valida     | "1st Note" | presente |
 | invalida   |   "   "    | ausente  |