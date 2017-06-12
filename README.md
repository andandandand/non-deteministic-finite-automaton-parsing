# **String Parsing with Nondeterministic Finite Automata** #

![nfa.png](https://bitbucket.org/repo/rndAKA/images/3388128771-nfa.png)

Java (6+) implementation of a [nondeterministic finite automaton](http://en.wikipedia.org/wiki/Nondeterministic_finite_automaton).  Strings are only accepted if they are consumed while the automaton is on a final state. The automaton's transitions and evaluated strings are defined in an input text file. Output declaring strings as accepted/rejected is printed in console and saved in output.txt. To execute call from terminal

```
#!bash

java NFAParsing < input.txt
```

##Input file definition##
* ***K*** (first line of the input file): specifies the number of automata. Each automaton case defines states, transitions and strings to parse. After ***K***, come ***K*** automata definitions. 

* ***S*** (first line of automaton's definition, first integer): defines the number of states for the automaton, which are numbered starting from 0.  

* ***T***  (first line of automaton's definition, second integer): defines the number of transitions for the automaton.

*  ***F*** (first line of automaton's definition, third integer): defines the number of final states for the automaton. After this line come ***F*** lines, each one listing a final state.

* After the listing of final states, come ***T*** lines with transition definitions. The first integer of each line is the origin state, the second integer is the destiny state, the character afterwards is a symbol that generates the state transition. 

* After the listing of transitions, comes a line with the integer ***S***. This integer is the number of strings that will be parsed. Next come the ***S*** strings that will be parsed. 

### input.txt 
1  

3 4 2  

1  

2  

0 1 a  

0 2 a 

1 1 b  

2 2 a  

5 

aa  

abb  

aba 

baa  

aaaaaa 

### output

Case 1:

Input: aa

Accepted


Input: abb

Accepted


Input: aba

Rejected


Input: baa

Rejected


Input: aaaaaa

Accepted


### License 

Affero GPL v3 License

#### **Author**: Antonio Rueda-Toicen

antonio "." rueda "." toicen [ at ] gmail
