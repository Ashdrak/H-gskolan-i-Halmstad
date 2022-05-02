



FACTORIAL
PUSH {R1, LR}
CMP R0, #0
; save working registers
; if input larger than zero
BGT FACTORIAL_DO ; ...do a recursion
MOV R0, #1
POP {R1, PC}
FACTORIAL_DO
MOV R1, R0
SUB R0, R0, #1
BL FACTORIAL
MUL R0, R0, R1
POP {R1, PC}
