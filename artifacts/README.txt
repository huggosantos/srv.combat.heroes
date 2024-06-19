# Orange Blood Heroes

**Orange Blood Heroes** é um divertido jogo de heróis de #SangueLaranja, baseado em turnos, que combatem para derrotar a temida entidade **Necab**.

## Dinâmica do jogo

Os heróis se agrupam em uma party e coperam entre si. A party possui um atributo que expressa a sua confiança; o seu entusiasmo (`morale`).

O jogo se resume em 2 turnos: **Combate** e **Descanso**.

### Turno de Combate

Cada herói faz um combate por vez, podendo ser um combate do tipo corpo-a-corpo (`melee`) ou o lançamento de um feitiço (`spell`).

Cada tipo de combate altera um atributo do herói:
- Combates do tipo `melee` alteram a saúde do personagem (`health`).
- Já combates do tipo `spell` alteram a energia mágica do personagem (`mana`).

O tipo do combate bem como o seu resultado influencia no destino do herói e também na morale da party. Segue a tabela:

|  Tipo do combate  |  Resultado do combate  | Consequência para o herói | Consequência para a party |
|:-----------------:|:----------------------:|---------------------------|---------------------------|
|      `melee`      |         `win`          | Incrementa 1 de `heatlh`  | Incrementa 1 de `morale`  |
|      `melee`      |         `lose`         | Decrementa 10 de `heatlh` | Decrementa 10 de `morale` |
|      `spell`      |         `win`          | Incrementa 1 de `mana`    | Incrementa 1 de `morale`  |
|      `spell`      |         `lose`         | Decrementa 10 de `mana`   | Decrementa 10 de `morale` |

Em resumo, vitórias somam 1 e derrotas diminuem 10.

### Turno de Descanso

Enquanto não está em combate, o #SangueLaranja descansa.

A cada intervalo de 10 segundos, tanto `health` quanto `mana` de cada herói recuperam em 2 pontos.

## Requisitos

1. Cada `#SangueLaranja` possui:
    1. um ID, que deve ser entre **0** e **4**.
    2. **health**, de no mínimo **0** e no máximo **100**, inclusive. Valor inicial é 100.
    3. **mana**, de no mínimo **0** e no máximo **100**, inclusive. Valor inicial é 100.
2. Um #SangueLaranja com health e/ou mana zerado não combatem.
3. A cada intervalo de 10 segundos todo #SangueLaranja tem os atributos incrementados em **2** pontos, respeitando o limite máximo de cada atributo.
4. A party:
    1. deve conter exatamente **5 #SanguesLaranja**.
    2. a `morale` da party deve ser de no mínimo **0** e no máximo **1000**, inclusive.
    3. a cada intervalo de 10 segundos, a `morale` da party é incrementada **20** pontos, respeitando o limite máximo.

## Ações

### Combate

Para registrarmos o resultado de um combate, chamaremos o método `GET /combat` passando alguns query params:

- `heroId`: o ID do #SangueLaranja, inteiro, obrigatório
- `combatType`: o tipo de combate, string, obrigatório. Valores possíveis: `melee` ou `spell`.
- `combatResult`: o resultado do combate, string, obrigatório. Valores possíveis: `win` ou `spell`.

Retornos esperados:

- `202 Accepted`: o combate é válido e foi computado com sucesso.
- `400 Bad Request`: o combate é inválido e não foi computado.
- `410 Resource Unavailable`: o herói não está em condições de combater no momento (requisito 2).

#### Exemplo 1

O herói 1 fez um combate corpo-a-corpo e venceu:

> GET /combat?heroId=0&combatResult=win&combatType=melee

#### Exemplo 2

O herói 5 fez um combate de feitiço e perdeu:

> GET /combat?heroId=5&combatResult=lose&combatType=spell
 
### Situação da party

Para obtermos a situação da party em um dado instante, chamaremos o método `GET /party`. Esse endpoint não recebe nenhum parâmetro.

> GET /party

Retornos esperados:

- `200 OK`: a representação da party em formato JSON, contendo os heróis com suas respectivas condições, a moral da party e o timestamp.

Segue abaixo um exemplo de retorno:

```json
{
  "party": {
     "heroes" : [
        {
           "id": 0,
           "health": 100,
           "mana": 100
        },
        
        {"...":"demais heroes aqui"}
     ],
     "morale": 1000
  },
  "timestamp": 1649175447
}
```

## Executando o projeto

Para executar o projeto, basta ir na raiz do projeto e rodar:

> `./mvnw spring-boot:run`

Obs.: é necessário o JDK 17 instalado na máquina.

----

# Objetivos

Os seus objetivos são:

- implementar o mecanismo de incremento/decremento de atributos, tanto proveniente da fase de combate quanto da fase de descanso, conforme requisitos.
- elencar pontos de melhorias ou de falhas da sua solução, se for o caso.
- aplicar conceitos de testes.
- quando solicitado, defender a sua solução para arquitetos do Inter via chamada de vídeo no Teams com compartilhamento da sua tela.
- o uso de banco de dados não é obrigatório, mas sinta-se a vontade para utilizar    

## Aspectos técnicos avaliados

Visando a transparência, segue abaixo a lista de aspectos técnicos que avaliaremos:

- Simplicidade
- Consistência
- Tolerância a falhas
- Desacoplamento
- Data design
- Concorrência
- Deployment
- Observabilidade

----

Vamos jogar?
