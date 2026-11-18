# Métodos GREEDY e Backtracking

Resumo para estudo e consulta, com base nos exercícios de **subconjuntos** (combinações) e **troco de moedas**.

---

## Método Guloso (Greedy)

### Ideia central
A cada passo, escolhe-se a **melhor opção local** (a que parece mais vantajosa naquele momento), sem nunca voltar atrás. A esperança é que uma sequência de boas escolhas locais leve a uma boa solução global.

### Como funciona
1. Define-se um **critério de escolha** (ex.: a maior moeda possível).
2. Escolhe-se o melhor candidato segundo esse critério.
3. Atualiza-se o problema (ex.: subtrai-se a moeda da quantia restante).
4. Repete-se até resolver o problema.

### Exemplo no exercício
Troco de 18 com moedas {5, 2, 1}:
- 18 → pega 5 (resta 13) → pega 5 (resta 8) → pega 5 (resta 3) → pega 2 (resta 1) → pega 1 (resta 0)
- Resultado: **5 moedas**

### Vantagens
- Simples de implementar.
- Muito rápido (geralmente O(n) ou O(n log n)).
- Usa pouca memória.

### Limitações
- **Não garante a solução ótima** para qualquer problema.
- Exemplo clássico: moedas {4, 3, 1} e quantia 6. O guloso dá 4+1+1 (3 moedas), mas o ótimo é 3+3 (2 moedas).
- Funciona apenas quando o problema tem a propriedade da **escolha gulosa** e **subestrutura ótima** (como em sistemas de moedas "canônicos", ex.: 5, 2, 1).

### Quando usar
Quando for possível provar que a escolha local leva ao ótimo global, ou quando uma solução aproximada rápida é suficiente.

### Problemas típicos
Troco (moedas canônicas), seleção de atividades, mochila fracionária, algoritmo de Dijkstra, árvore geradora mínima (Prim e Kruskal), codificação de Huffman.

---

## Backtracking (Retrocesso)

### Ideia central
Constrói a solução **passo a passo**, testando escolhas. Se uma escolha leva a um beco sem saída (ou já foi usada), **desfaz** a decisão (retrocede) e tenta a próxima alternativa. É uma busca exaustiva e organizada, com **poda** dos caminhos que não podem dar certo.

### Como funciona (padrão: escolher, explorar, desfazer)
1. **Escolhe** uma opção e a adiciona à solução parcial.
2. **Explora** recursivamente a partir dessa escolha.
3. **Desfaz** a escolha (remove da solução parcial) e tenta a próxima opção.

### Esqueleto genérico

```java
void backtrack(estadoAtual) {
    if (soluçãoCompleta(estadoAtual)) {
        guardarSolução(estadoAtual);
        return;
    }
    for (opção : opçõesDisponíveis(estadoAtual)) {
        if (!éVálida(opção)) continue;   // poda
        aplicar(opção);                  // escolhe
        backtrack(novoEstado);           // explora
        desfazer(opção);                 // desfaz
    }
}
```

### Exemplo no exercício
Subconjuntos de 2 elementos de S = [1, 2, 3]:
- Escolhe 1 → escolhe 2 → tamanho 2 atingido, guarda **[1, 2]** → desfaz o 2
- Escolhe 3 → guarda **[1, 3]** → desfaz o 3 → desfaz o 1
- Escolhe 2 → escolhe 3 → guarda **[2, 3]**

### Vantagens
- **Encontra todas as soluções** (ou a ótima, se comparar todas).
- Funciona em problemas onde o guloso falha.
- A poda reduz muito o espaço de busca.

### Limitações
- Pode ter custo **exponencial** no pior caso.
- Fica lento para entradas grandes sem boas podas.

### Quando usar
Problemas de **enumeração** (gerar todas as combinações, permutações, subconjuntos) e de **busca com restrições**.

### Problemas típicos
Combinações e permutações, N-Rainhas, Sudoku, labirintos, soma de subconjuntos, coloração de grafos.

---

## Comparação rápida

| Característica | Guloso | Backtracking |
|---|---|---|
| **Estratégia** | Melhor escolha local, sem voltar atrás | Testa possibilidades e desfaz se necessário |
| **Retrocede?** | Não | Sim |
| **Garante o ótimo?** | Só em problemas específicos | Sim (explora todas as possibilidades) |
| **Velocidade** | Muito rápida | Pode ser exponencial |
| **Complexidade de código** | Simples | Recursão + controle de estado |
| **Resultado típico** | Uma solução | Todas as soluções |
| **Exemplo no exercício** | Troco com {5, 2, 1} | Subconjuntos de n elementos |

---

## Como escolher

1. O problema tem a **propriedade da escolha gulosa**? Se sim, use o **guloso**.
2. Precisa **listar todas as soluções** ou o guloso não garante o ótimo? Use **backtracking** (ou **programação dinâmica**, se houver subproblemas repetidos).
3. Em dúvida, comece pelo backtracking para ter a resposta correta e otimize depois.

---

## Dica para estudo

Se o guloso falhar, o **contraexemplo** é a melhor prova. Para o troco, teste {4, 3, 1} com quantia 6 e veja a diferença:

| Método | Resultado | Moedas |
|---|---|---|
| Guloso | 4 + 1 + 1 | 3 |
| Ótimo (DP/backtracking) | 3 + 3 | 2 |