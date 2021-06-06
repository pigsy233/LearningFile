clear;
clc;


room = imread('room.tif');
number = imread('number.tif');

%show image
subplot(2,6,1);
imshow(room);
title('原始图像');
subplot(2,6,7);
imshow(number);
title('原始图像');

Room = fft2(room);
Number = fft2(number);


%show image
subplot(2,6,2);
imshow(Room);
title('傅里叶变换');
subplot(2,6,8);
imshow(Number);
title('傅里叶变换');


F_Room = fftshift(log(1+abs(Room)));
F_Number = fftshift(log(1+abs(Number)));


%show image
subplot(2,6,3);
imshow(Room);
title('中心化');
subplot(2,6,9);
imshow(Number);
title('中心化');


F_Room = gscale(F_Room);
F_Number = gscale(F_Number);


h = fspecial('sobel');                     
Filter_Room = paddedsize(size(room));   
Sobel_Filter_Room = freqz2(h,Filter_Room(1),Filter_Room(2)); 
Sobel_Filter_Room_Reorder = ifftshift(Sobel_Filter_Room);
Filter_Number = paddedsize(size(number));   
Sobel_Filter_Number = freqz2(h,Filter_Number(1),Filter_Number(2)); 
Sobel_Filter_Number_Reorder = ifftshift(Sobel_Filter_Number);
Room_gs = imfilter(double(room),h);  
Room_gf = dftfilt(room,Sobel_Filter_Room_Reorder);         
Number_gs = imfilter(double(number),h);  
Number_gf = dftfilt(number,Sobel_Filter_Number_Reorder);   

%show image
subplot(2,6,4);
imshow(Room_gs,[]);
title('空域滤波');
subplot(2,6,10);
imshow(Number_gs,[]);
title('空域滤波');
subplot(2,6,5);
imshow(Room_gf,[]);
title('频域滤波');
subplot(2,6,11);
imshow(Number_gf,[]);
title('频域滤波');

PQ=paddedsize(size(number));  
D0=0.05*PQ(1);        
H=hpfilter('gaussian',PQ(1),PQ(2),D0);    
F_Room_Signal = dftfilt(room,H);
F_Number_Signal = dftfilt(number,H);


%show image

subplot(2,6,6);
imshow(F_Room_Signal,[]);
title('频域滤波');
subplot(2,6,12);
imshow(F_Number_Signal,[]);
title('频域滤波');
