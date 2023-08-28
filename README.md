# DEISI Jungle
![](deisi-jungle.jpg?raw=true "Deisi Jungle")

<p align="justify"> Certo dia, numa savana muito (muito) longe daqui, os animais decidiram descobrir quem seria
o melhor atleta entre eles. Para o fazer, definiram um conjunto de provas desportivas que iriam
disputar entre si — quem vencesse mais provas seria considerado o Rei da Selva. Rapidamente
a notícia chegou ao Tarzan, que não quis deixar de participar nas provas, convencido de que
facilmente as venceria. A primeira prova a disputar é a prova de atletismo. Os animais vão-se reunir numa pista e
competir para determinar qual o melhor atleta. </p>

# Diagrama UML
![](diagrama.png?raw=true "Diagrama UML")

### Escolhas de modelação

##### Classes
- <p align="justify">GameManager — responsável por gerir o jogo.</p>
  <br/>
- <p align="justify">Jogador — representa a classe que encapsula informações e funcionalidades específicas dos jogadores no jogo.
   Armazena atributos relevantes para a caracterização dos jogadores e define o seu comportamento durante o jogo.</p>
  <br/>
- <p align="justify">MovementResult — representa o resultado de um movimento realizado por um jogador.
  Caracteriza-se por uma variável "code" do tipo MovementResultCode e, opcionalmente, por uma variável "message" 
  do tipo String, que apresenta uma mensagem descritiva no visualizador.</p>
  <br/>
- <p align="justify">MovementResultCode — enumeração que define os possíveis códigos de resultado para um movimento.
    Inclui os valores INVALID_MOVEMENT, NO_ENERGY, VALID_MOVEMENT e CAUGHT_FOOD, que correspondem a diferentes situações do jogo, 
    como erros de movimento, falta de energia, movimento válido e interação com alimentos.</p>
  <br/>
- <p align="justify">InvalidInitialJungleException — classe responsável por lançar exceções em situações 
  de configuração inicial inválida do mapa de jogo.
  Especialmente relacionada à composição e posição de elementos como jogadores e alimentos. 
  Esta exceção é acionada quando as condições iniciais não estão de acordo com as regras do jogo.</p>
  <br/>

##### Classes abstratas

  <p align="justify">As espécies trazem variedade ao jogo e dão vida às diferentes espécies que os jogadores podem escolher.</p>

- <p align="justify">Especie — com um papel essencial no jogo, a classe Especie representa todas as espécies presentes. 
            Contém os atributos essenciais para caracterizar as diferentes espécies em jogo, junto com os métodos e funções 
            que processam os comportamentos específicos de cada espécie durante o jogo, de forma a garantir que cada espécie seja única.</p>
  

  ##### Classes filho (Especie)

      > Elefante — contém as informações específicas sobre espécie elefante, métodos/funções que processam os comportamentos relevantes da especie elefante para o contexto do jogo. 
      > Leao — contém as informações específicas sobre espécie leão, os métodos/funções que processam os comportamentos relevantes da especie leão para o contexto do jogo.
      > Passaro — contém as informações específicas sobre espécie pássaro, os métodos/funções que processam os comportamentos relevantes da especie pássaro para o contexto do jogo.
      > Tartaruga — contém as informações específicas sobre espécie tartaruga, os métodos/funções que processam os comportamentos relevantes da especie tartaruga para o contexto do jogo.
      > Tarzan — contém as informações específicas sobre espécie tarzan, os métodos/funções que processam os comportamentos relevantes da especie tarzan para o contexto do jogo.
      > Unicornio — contém as informações específicas sobre espécie unicórnio, os métodos/funções que processam os comportamentos relevantes da especie unicórnio para o contexto do jogo.

  
 <br/>

  <p align="justify">Ao longo do terreno, estarão espalhados vários alimentos, de diferentes tipos. Quando um
  jogador calha numa casa com um alimento, ele é obrigado a ingeri-lo. Isso pode ser bom ou
  mau.</p>

- <p align="justify">Alimento — essencial para o jogo, representa todos os tipos de alimentos disponíveis.
  Contém os atributos para caracterizar os alimentos em jogo, bem como os métodos e funções 
  que processam os comportamentos relevantes de cada alimento. Tal como a classe abstrata Especie, 
  o código evita redundâncias, proporcionando uma base sólida dos conceitos de herança e o polimorfismo.</p>

  ##### Classes filho (Alimento)

      > Agua — contém as informações específicas sobre a água, métodos/funções que processam os comportamentos relevantes da água para o contexto do jogo.
               ○ Se ingerido por carnívoros ou herbívoros, aumenta a energia em 15 unidades
               ○ Se ingerido por omnívoros, aumenta a energia em 20%

      > Erva — contém as informações específicas sobre a erva, métodos/funções que processam os comportamentos relevantes da erva para o contexto do jogo.
               ○ Se ingerido por herbívoros ou omnívoros, aumenta a energia em 20 unidades
               ○ Se ingerido por carnívoros, reduz a energia em 20 unidades

      > CachoDeBanana — contém as informações específicas sobre os cachos de banana, métodos/funções que processam os comportamentos relevantes dos cachos de bananas 
               para o contexto do jogo.
               ○ Pode ser ingerido por qualquer animal
               ○ Aumenta a energia em 40 unidades
               ○ Só existem 3 bananas no cacho. Os animais que calham numa casa com este
               alimento consomem uma dessas bananas. Quando as bananas acabarem, o
               alimento deixa de produzir efeito. Ou seja, a partir da 4ª vez que algum animal
               chega a uma casa contendo este alimento, ele já não é afetado.
               ○ Como comer muitas bananas causa dificuldades gástricas, se o mesmo jogador
               consumir mais do que uma banana, a 2ª e 3ª bananas retiram energia em vez de
               dar, na mesma proporção (40U).

      > Carne — contém as informações específicas sobre a carne, métodos/funções que processam os comportamentos relevantes da carne para o contexto do jogo.
               ○ Se ingerido por carnívoros (ex: Leão) ou omnívoros (ex: Tarzan), aumenta a
               energia em 50 unidades.
               ○ Os herbívoros ignoram esta comida, por isso não lhes acontece nada.
               ○ Deteriora-se à medida que o tempo passa. Só é comestível nas primeiras 12
               jogadas. A partir daí é tóxica - se fôr ingerida, reduz para metade a energia do
               animal.

      > CogumeloMagico — contém as informações específicas sobre os cogumelos mágicos, métodos/funções que processam os comportamentos relevantes dos cogumelos mágicos 
               para o contexto do jogo.
               ○ Todos os animais podem ingerir.
               ○ Como são mágicos, o seu comportamento varia de cogumelo para cogumelo e
               de jogada para jogada.
               ○ Cada cogumelo tem associado um número (N) entre 10 e 50, que é gerado
               aleatoriamente na sua criação.
               ○ Se comerem o cogumelo nas jogadas pares, os animais aumentam em N% a
               sua energia.
               ○ Se comerem o cogumelo nas jogadas ímpares, ele torna-se venenoso e
               reduzem em N% a sua energia.
  

### Justificação das escolhas de modelação

> Evitar duplicação de código:
> 
  <p align="justify"> Através da aplicação de herança e polimorfismo, o foco era evitar redundâncias no código, 
    para facilitar na implementação, legibilidade e compreensão da lógica do jogo. 
    Ao compartilhar comportamentos comuns através das classes base, como Especie e Alimento, cada classe filha 
    foi habilitada a ter comportamentos específicos e únicos.</p>

> Definir características comuns e manter comportamentos distintos: 
> 
  <p align="justify"> A herança forneceu a base para uma estrutura centralizada de características compartilhadas entre todas as espécies e alimentos. 
    Isso permitiu a incorporação flexível de comportamentos únicos para cada espécie e alimento. Essa abordagem
    facilitou a compreensão da lógica do jogo e a implementação de novas funcionalidades sem afetar outras partes do código.</p>

# Video de demonstração
- por definir...

## Tela do jogo
![alt_text](The_jungle.png?raw=true "Deisi Jungle")