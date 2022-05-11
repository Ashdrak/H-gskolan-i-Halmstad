        NAME    main
        PUBLIC  main
        SECTION .text : CODE (2)
        THUMB

SYSTICK_CTRL  EQU 0xE000E010
SYSTICK_LOAD  EQU 0xE000E014
SYSTICK_VAL   EQU 0xE000E018

main
        LDR R0, =SYSTICK_CTRL
        MOV R1, #0
        STR R1, [R0]
        
        LDR R0, =SYSTICK_VAL
        STR R1, [R0]
        
        LDR R0, =SYSTICK_LOAD
        LDR R1, =49999
        STR R1, [R0]
       
        LDR R0, =SYSTICK_CTRL
        MOV R1, #0x07
        STR R1, [R0]
STOP    B STOP
        END