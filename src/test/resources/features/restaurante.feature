#Author: leonardo_requena@outlook.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Restaurante
  I want to use this template for my feature file

  @tag1
  Scenario: Registrar resturante
    Given que estou testando o cenário de registro de restaurantes
    When eu registrar um restaurante
    Then devo receber um created
    
    
  @tag2
  Scenario: Buscar resturante por nome
    Given necessidade de buscar um ou mais restaurante por nome
    When eu buscar o restaurante por nome
    Then devo receber uma lista de restaurantes com o nome solicitado
    
  @tag3
  Scenario: Buscar resturante por gastronomia
    Given necessidade de buscar um ou mais restaurante por gastronomia
    When eu buscar o restaurante por gastronomia
    Then devo receber uma lista de restaurantes com a gastronomia solicitada
    
  @tag4
  Scenario: Buscar resturante por cidade
    Given necessidade de buscar um ou mais restaurante por cidade
    When eu buscar o restaurante por cidade
    Then devo receber uma lista de restaurantes com a cidade solicitada
    
    