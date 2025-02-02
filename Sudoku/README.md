# Projet APO : Sudoku

- Clément RENIERS
- Louison PARANT
- Luka COUTANT


## Methodologie

#### GIT

https://github.com/VirisOnGithub/Projet-APO

#### Déroulement

La première étape a été la conception du projet.  
Nous avons commencé par une ébauche de diagramme de cas d'utilisation, 
qui a permis un premier jet de notre vision.  
Ensuite, nous avons réalisé un premier diagramme de classe, pour commencer l'implémentation.

Nous avons ensuite commencé à coder, avec un premier jet pour les sudokus classiques, sans aucune notion de blocs.

Nous sommes dans un troisième temps revenu à la conception, pour nous rendre compte que nous aurions besoin des blocs pour pouvoir créer les sudokus et les résoudre plus efficacement.

Enfin nous avons implémenté les blocs, et avons pu implémenter les méthodes de résolution.

### Choix de conception

Nous avons choisi de créer des `Pair`, classe qui semble plus naturelle pour représenter les chiffres dans la grille (couple valeur/bloc).  
Nous avons également utilisé des `ArrayLists`, qui sont beaucoup plus adaptées pour manipuler des données de taille variable.  
L'utilisation des `streams` de Java nous a également contraint à utiliser des `AtomicInteger` pour pouvoir modifier la valeur des blocs.  
Pour les Multidokus, nous avons choisi de les représenter par une `ArrayList` de `Sudoku` avec des `"bindBlocks"` qui permet de lier les valeurs de certains blocs des grilles.
