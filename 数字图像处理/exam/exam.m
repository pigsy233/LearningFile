clc;
close;
clear;

slit1 = imread('10-807J.jpg');
slit1 = im2gray(slit1);
slit1 = imgaussfilt(slit1,5);
% slit = imfilter(slit,fspecial('average',[3,3]));
[Tf1 SMf1] = graythresh(slit1);

slit2 = imread('10-258Z2.jpg');
slit2 = im2gray(slit2);
slit2 = imgaussfilt(slit2,5);
% slit = imfilter(slit,fspecial('average',[3,3]));
[Tf2 SMf2] = graythresh(slit2);

slit3 = imread('10-258Z1.jpg');
slit3 = im2gray(slit3);
slit3 = imgaussfilt(slit3,5);
% slit = imfilter(slit,fspecial('average',[3,3]));
[Tf3 SMf3] = graythresh(slit3);


%用edge算法对二值化图像进行边缘提取

Pic1 = im2bw(slit1, SMf1 - 0.15); 

PicEdge1 = edge(Pic1,'sobel',0.4);
figure();
imshow(PicEdge1);
title('10-807J sobel算子');

Pic2 = im2bw(slit2, SMf2 - 0.1625); 

PicEdge2 = edge(Pic2,'sobel',0.1);
figure();
imshow(PicEdge2);
title('10-258Z2 sobel算子');

Pic3 = im2bw(slit3, SMf3 - 0.2); 

PicEdge3 = edge(Pic3,'sobel',0.2);
figure();
imshow(PicEdge3);
title('10-258Z1 sobel算子');


