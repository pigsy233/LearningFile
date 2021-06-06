clc;
clear;

rgb_image = imread('flower.tif');   %读取图像flower1.tif
fR = rgb_image(:,:,1);            %获取图像的红色分量
fG = rgb_image(:,:,2);            %获取图像的绿色分量
fB = rgb_image(:,:,3);            %获取图像的蓝色分量
f1 = figure('Name','RGB','NumberTitle','off');
subplot(2,2,1);
imshow(rgb_image);
title('原图像');
subplot(2,2,2);
imshow(fR);
title('Red');
subplot(2,2,3);
imshow(fG);
title('Green');
subplot(2,2,4);
imshow(fB);
title('Blue');

yiq_image = rgb2ntsc(rgb_image); 
fY = yiq_image(:,:,1);           %图像flower1.tif的亮度
fI = yiq_image(:,:,2);            %图像flower1.tif的色调
fQ = yiq_image(:,:,3);           %图像flower1.tif的饱和度
f2 = figure('Name','NATURE','NumberTitle','off');
subplot(2,2,1);
imshow(rgb_image);
title('原图像');
subplot(2,2,2);
imshow(fY);
title('亮度');
subplot(2,2,3);
imshow(fI);
title('色调');
subplot(2,2,4);
imshow(fQ);
title('饱和度');

fR_E=histeq(fR,256);      %对彩色图像的分量进行直方图均衡化
fG_E=histeq(fG,256);
fB_E=histeq(fB,256);
RGB_image=cat(3,fR_E,fG_E,fB_E);  %将直方图均衡化后的彩色图像合并
f3 = figure('Name','RGB均衡化','NumberTitle','off');
subplot(2,2,1);
imshow(rgb_image);
title('原图像');
subplot(2,2,2);
imshow(fR_E);
title('Red');
subplot(2,2,3);
imshow(fG_E);
title('Green');
subplot(2,2,4);
imshow(fB_E);
title('Blue');

ture_color=cat(3,fR,fG,fB);
f4 = figure('Name','合成图像','NumberTitle','off');
subplot(1,3,1);
imshow(ture_color)    %显示由红、绿、蓝三幅图合成的彩色图
title('真彩');
false_color=cat(3,fR_E,fG,fB);    %用近红外图像代替R分量
subplot(1,3,2);
imshow(false_color)   %显示由近红外、绿、蓝三幅图合成的假彩色图
title('假彩');
false_color=cat(3,fR_E,fG_E,fB_E);    %用近红外图像代替R分量
subplot(1,3,3);
imshow(false_color)   %显示由近红外、绿、蓝三幅图合成的假彩色图
title('假彩');

f=imread('head.jpg');
cut_1=imadjust(f,[0.0925 0.5],[0.0925 0.5]);%提取灰度在16-128之间的像素
cut_2=imadjust(f,[0.5 1],[0.5 1]);         %提取灰度在128-256之间的像素
f4 = figure('Name','图像彩色化','NumberTitle','off');
imshow(cut_1),colormap(hot)  %显示图像cut_1,并使用hot模型彩色化
imshow(cut_2),colormap(cool) %显示图像cut_2,并使用cool模型彩色化



