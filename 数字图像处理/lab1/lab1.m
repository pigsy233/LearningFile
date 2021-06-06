clc;
clear;

%1-3
flower = imread("flower.tif");
whos flower;
imshow(flower);


%4-6
imwrite(flower,'flower.jpg','quality',50);
Flower_Info = imfinfo('flower.jpg') ;
imwrite(flower,'flower.bmp');
fprintf('the size of flower.jpg is %d bytes\n',Flower_Info.FileSize);
%disp(Flower_Info.FileSize);


%7-9
Lenna = imread('lenna.jpg');
Camera = imread('camera.jpg');
Lenna_Info = imfinfo('lenna.jpg');
Camera_Info = imfinfo('camera.jpg');
fprintf('the size of lenna.jpg is %d bytes\n',Lenna_Info.FileSize);
fprintf('the size of camera.jpg is %d bytes\n',Camera_Info.FileSize);
f1 = figure('Name','Lenna','NumberTitle','off');
f2 = figure('Name','Camera','NumberTitle','off');
figure(f1);
%imshow('lenna.jpg');
imshow(Lenna);
figure(f2);
imshow(Camera);
%imshow('camera.jpg');


%10-11
f3 = figure('Name','Logical_Flower','Numbertitle','off');
Logical_Flower = im2bw(flower);
%Logical_Flower = imbinarize('flower.jpg');
imshow(Logical_Flower);

%thinking
f4 = figure('Name','operator','NumberTitle','off');
subplot(2,2,1)
imshow(Lenna + 100);
title('plus 100')
subplot(2,2,2)
imshow(Lenna - 100);
title('minus 100');
subplot(2,2,3);
imshow(Lenna * 5)
title('multiply 5')
subplot(2,2,4)
imshow(Lenna / 5)
title('divide 5')