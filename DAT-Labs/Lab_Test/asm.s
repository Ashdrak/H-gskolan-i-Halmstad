      NAME main
      PUBLIC main
      SECTION .text: CODE (2)
      THUMB
           
main
     LDR R4, =Vector                  ; Load our vector into R4
     LDR R6, [R4]                     ; first element of vector
     LDR R7, [R4]                     ; first element of vector
     MOV R8, #0                       ; set R8 to 0
     
     ; counter
     MOV R2, #0                       ; set R2 to 0

     ; vector size counter
     MOV R3, #0                       ; set R3 to 0

     ; Here we are looping thrue the elements to count them. 
VESI LDR R11, [R4,R3,LSL #2]          ; vector[i]
     CMP R11, #0xABABABAB             ; Looking for a specifik character thats added in the end of our array. If found end loop
     BEQ LOOP                         ; Jump to LOOP
     ADD R3, R3, #1                   ; increase our vector size counter with 1
     B VESI                           ; Jump to VESI
     
LOOP CMP R2,R3                        ; if R2 = R3 lOOP stops
     BEQ DIVI                         ; Jump to DIVI
     LDR R0, [R4,R2,LSL #2]           ; vector[i]
     ADD R8, R8, R0                   ; adds our elements into a sum
     CMP R0, R7                       ; if R0 bigger then R7 jump to FMAX
     BHI FMAX                         ; Jump to FMAX
     CMP R0, R6                       ; if R5 is smaller then R6 jump to FMIN
     BLO FMIN                         ; Jump to FMIN
     ADD R2, R2, #1                   ; increase our counter with 1
     B LOOP                           ; Jump to Loop
FMAX MOV R7, R0                       ; adds our current Max to R7
     ADD R2, R2, #1                   ; increase our counter with 1
     B LOOP                           ; Jump to LOOP
FMIN MOV R6, R0                       ; adds our current min to R6
     ADD R2, R2, #1                   ; increase our counter with 1
     B LOOP                           ; Jump to LOOP
DIVI UDIV R8, R8, R3                  ; divides R8 with R3
STOP B STOP                           ; End with infint loop
     
     ALIGNROM 2
     DATA
Vector
     DC32 14, 25, 2, 27, 3
     DC32 22, 13, 4, 24, 6
     DC32 26, 18, 8, 15, 9
     DC32 28, 10, 7, 17, 5
     DC32 0xABABABAB                  ; vector end point
     END 