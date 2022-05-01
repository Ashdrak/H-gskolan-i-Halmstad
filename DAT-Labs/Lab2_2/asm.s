        NAME    main
        PUBLIC  main
        SECTION .text : CODE (2)
        THUMB
main
     MOV R0, #0 ; counter
     MOV R1, #0 ; Compare value to our 32-bit number
     MOV R2, #0 ; How many bits differ from R3,R4
     LDR R3, =0xCDFCDFC
     LDR R4, =0xA24F1BF
     BL HamDistReg
STOP B STOP ; infinite walk

HamDistReg
     EOR R1, R3, R4
COUNTER
     CMP R0, #32 ; compare the value of r0 agains 32 if they are the same the loop stops
     BEQ STOP
     LSLS R1, #1
     ADC R2, #0  ;adds the carry to r2
     ADD R0, R0, #1 ;increases our counter
     B COUNTER
     END
     