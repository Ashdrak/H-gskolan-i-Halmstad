      NAME main
      PUBLIC main
      SECTION .text: CODE (2)
      THUMB
           
main
     ;size of vector
     LDR R3, =size 
     LDR R3, [R3] ; vector size
     SUB R3, R3, #1 ; vector size -1 for end point of loop 
     ;max size
     LDR R4, =Vector
     LDR R6, [R4] ;first element of vector
     LDR R7, [R4] 
     LDR R8, [R4] 
     ; counter
     MOV R2, #0 
     ;loop max value
LOOP CMP R2,R3 ; if R2 => R3 loop stops
     BGE CON1
     LDR R0, [R4,R2,LSL #2] ; vector[i]
     CMP R0, R7 ; compare our elemtents 
     BHI FMAX ; if R0 bigger then R6 jump to FMAX
     ADD R2, R2, #1 ; adds 1 to our conter
     B LOOP ;Jump to Loop
FMAX MOV R7, R0 ; 
     ADD R2, R2, #1
     B LOOP
     
     ;Loop min value
CON1 MOV R2, #0
LOP2 CMP R2,R3
     BGE CON2
     LDR R5, [R4,R2,LSL #2]
     CMP R5, R6
     BLT FMIN
     ADD R2, R2, #1
     B LOP2
FMIN MOV R6, R5
     ADD R2, R2, #1
     B LOP2
     
     ;Average Value
CON2 ;LDR R5, [R4]
     MOV R2, #0
LOP3 CMP R2,R3 ; if R2 => R3 loop stops
     BGE DIVI
     LDR R0, [R4,R2,LSL #2]
     ADD R8, R8, R0
     ADD R2, R2, #1
     B LOP3
DIVI UDIV R8, R8, R3
     B STOP
STOP B STOP
     ALIGNROM 2
     DATA
Vector
     DC32 14, 25, 2, 27, 3
     DC32 22, 13, 4, 24, 6
     DC32 26, 18, 8, 15, 9
     DC32 28, 10, 7, 17, 5
size
     DC32 20
     END 